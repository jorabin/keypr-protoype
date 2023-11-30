package com.thebuildingblocks.keypr.helper.server;

import com.thebuildingblocks.keypr.helper.tools.TestHelperServer;

import java.io.IOException;

import static com.thebuildingblocks.keypr.common.TestIds.DEFAULT_IDS;

public class Main {
    public static void main(String[] args) throws IOException {
        TestHelperServer ths = new TestHelperServer();
        String port = System.getenv("PORT");
        if (port == null) {
            port = "8080";
        }
        ths.startServer(Integer.parseInt(port), DEFAULT_IDS);
    }
}