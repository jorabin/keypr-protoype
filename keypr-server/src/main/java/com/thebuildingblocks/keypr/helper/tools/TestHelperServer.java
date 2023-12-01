/*
 * Copyright (c) 2023 The Building Blocks Limited.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.thebuildingblocks.keypr.helper.tools;

import com.google.protobuf.ByteString;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import com.thebuildingblocks.keypr.common.TestIds;
import com.thebuildingblocks.keypr.helper.HelperServerResponseProcessing;
import com.thebuildingblocks.keypr.helper.HelperServerShare;
import org.derecalliance.derec.api.DeRecIdentity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class TestHelperServer {

    public static void main(String[] args) throws IOException {
        TestHelperServer ths = new TestHelperServer();
        ths.startServer(8080, TestIds.INSTANCE.defaultIds);
        System.out.println("Hit enter to exit");
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
        ths.stopServer();
    }

    static Logger logger = LoggerFactory.getLogger(TestHelperServer.class);
    HttpServer server;
    private final List<HttpContext> contexts = new ArrayList<>();


    public void startServer(int port, List<DeRecIdentity> ids) throws IOException {
        logger.info("Starting server on port {}", port);
        server = HttpServer.create(new InetSocketAddress(port), 10);
        for (DeRecIdentity id : ids) {
            HttpContext context = server.createContext(id.getAddress().getPath(), new HelperHandler(id));
            logger.info("Started helper {}", context.getPath());
            contexts.add(context);
        }
        server.createContext("/", new DefaultHandler());
        logger.info("Server started");
        server.start();
    }

    public void stopServer() {
        server.stop(0);
        System.out.println("Server stopped");
    }

    public HttpServer getServer() {
        return server;
    }

    public List<HttpContext> getContexts() {
        return contexts;
    }

    public static class DefaultHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            exchange.sendResponseHeaders(400, 0);
            try (OutputStream o = exchange.getResponseBody()) {
                o.write(("<body style=\"font-family:sans-serif\">" +
                        "<h1>Keypr Server</h1>" +
                        "<p>That is not a known helper</p>" +
                        "</body>").getBytes(StandardCharsets.UTF_8));
            }
        }
    }

    public static class HelperHandler implements HttpHandler {

        private final HelperServerResponseProcessing helperServerResponseProcessing;
        private final HelperServerShare.Storage storage = new HelperServerShare.SimpleStorage();

        public HelperHandler(DeRecIdentity id) {
            this.helperServerResponseProcessing = new HelperServerResponseProcessing(id, storage);
        }

        @Override
        public void handle(HttpExchange exchange) {
            try {
                this.helperServerResponseProcessing.process(exchange.getRequestBody(), exchange.getResponseBody(),
                        () -> exchange.sendResponseHeaders(200, 0));

            } catch (Throwable t) {
                logger.error("Exception processing message: {}", t.getMessage());
                try {
                    exchange.sendResponseHeaders(400, 0);
                    try (OutputStream o = exchange.getResponseBody()) {
                        o.write(("<body style=\"font-family:sans-serif\">" +
                                "<h1>Keypr Server</h1>" +
                                "<p>Error: "+ t.getMessage() + "</p>" +
                                "</body>").getBytes(StandardCharsets.UTF_8));
                    }
                } catch (IOException e) {
                    throw new RuntimeException("Error sending error response", e);
                }
            }
        }

    }
}
