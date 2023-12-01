package com.thebuildingblocks.keypr.sharer;

import com.thebuildingblocks.keypr.common.TestIds;
import com.thebuildingblocks.keypr.helper.tools.TestHelperServer;
import com.thebuildingblocks.keypr.sharer.tools.Notifier;
import com.thebuildingblocks.keypr.sharer.tools.Recipes;
import org.derecalliance.derec.api.DeRecHelperStatus;
import org.derecalliance.derec.api.DeRecIdentity;
import org.derecalliance.derec.api.DeRecSecret;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.*;
import java.util.stream.Collectors;

import static com.thebuildingblocks.keypr.common.Cryptography.keyPairGenerator;
import static com.thebuildingblocks.keypr.common.Cryptography.pemFrom;
import static org.junit.Assert.*;

public class RecoveryTest {
    Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    @Test
    public void ListSecretIdsVersionsTest() throws IOException, ExecutionException, InterruptedException, TimeoutException {
        TestHelperServer server = new TestHelperServer();
        server.startServer(8080, TestIds.INSTANCE.defaultIds);
        try {
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            String pem = pemFrom(keyPair.getPublic());
            // build a sharer
            Sharer me = Sharer.newBuilder()
                    .id(new DeRecIdentity("Incremental Inge", "mailto:test@example.org", null, pem))
                    .keyPair(keyPair)
                    .notificationListener(Notifier::logNotification)
                    .build();

            // build a secret and wait for results from all
            Secret secret1 = me.newSecret("A test secret", "some secret or other".getBytes(StandardCharsets.UTF_8));
            //secret1.retryParameters.connectTimeout= Duration.ofSeconds(1);
            List<CompletableFuture<? extends DeRecHelperStatus>> futures = secret1.addHelpersAsync(TestIds.INSTANCE.defaultIds);
            CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).get(6, TimeUnit.SECONDS);
            assertEquals(server.getContexts().size(), secret1.getPairedHelpers());
            secret1.update(secret1.getVersions().lastEntry().getValue().getProtectedValue());

            // update and wait for threshold acknowledgements
            secret1.update("a newfangled version".getBytes(StandardCharsets.UTF_8));

            // start a new secret, share and block
            Secret secret2 = me.newSecret("my second secret",
                    "very hush hush".getBytes(StandardCharsets.UTF_8),TestIds.INSTANCE.defaultIds);


            // secrets should have same helpers
            Set<DeRecIdentity> helpers1 = secret1.getHelpers().stream()
                    .filter(h -> h.getStatus().equals(DeRecHelperStatus.PairingStatus.PAIRED))
                    .map(HelperClient::getId)
                    .collect(Collectors.toSet());
            Set<DeRecIdentity> helpers2 = secret2.getHelpers().stream()
                    .filter(h -> h.getStatus().equals(DeRecHelperStatus.PairingStatus.PAIRED))
                    .map(HelperClient::getId)
                    .collect(Collectors.toSet());
            helpers2.removeAll(helpers1);
            assertEquals(0, helpers2.size());

            // loop over helpers
            for (DeRecIdentity identity: helpers1) {
                //get the secrets and versions they hold
                Future<Map<DeRecSecret.Id, List<Integer>>> future = me.getSecretIdsAsync(identity);
                try {
                    // block for result
                    Map<DeRecSecret.Id, List<Integer>> secretIdsVersions = future.get(3, TimeUnit.SECONDS);
                    // check it has secret1
                    assertTrue(secretIdsVersions.containsKey(secret1.getSecretId()));
                    assertEquals(identity.getName(),2, secretIdsVersions.get(secret1.getSecretId()).size());
                    assertArrayEquals(secretIdsVersions.get(secret1.getSecretId()).toArray(),
                            Recipes.listVersionNumbersForHelper(secret1, identity).toArray());

                    assertTrue(secretIdsVersions.containsKey(secret2.getSecretId()));
                    assertEquals(identity.getName(),1, secretIdsVersions.get(secret2.getSecretId()).size());
                    assertArrayEquals(secretIdsVersions.get(secret2.getSecretId()).toArray(), secret2.getVersions().keySet().toArray());
                    logger.info("Completed {}", identity.getName());
                } catch (ExecutionException | TimeoutException | InterruptedException e) {
                    fail(e.getMessage());
                }
            }
        } finally {
            server.stopServer();
        }
    }
}