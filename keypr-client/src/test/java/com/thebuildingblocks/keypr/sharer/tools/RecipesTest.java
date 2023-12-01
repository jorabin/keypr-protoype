package com.thebuildingblocks.keypr.sharer.tools;

import com.thebuildingblocks.keypr.common.TestIds;
import com.thebuildingblocks.keypr.sharer.Secret;
import com.thebuildingblocks.keypr.sharer.Sharer;
import org.derecalliance.derec.api.DeRecIdentity;
import com.thebuildingblocks.keypr.helper.tools.TestHelperServer;
import org.junit.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.util.Arrays;

import static com.thebuildingblocks.keypr.common.Cryptography.keyPairGenerator;
import static com.thebuildingblocks.keypr.common.Cryptography.pemFrom;

public class RecipesTest {
    static TestHelperServer server;
    Sharer sharer;
    Secret secret1, secret2;

    @BeforeClass
    public static void setUpServer() throws IOException {
        server = new TestHelperServer();
        server.startServer(8080, TestIds.INSTANCE.defaultIds);
    }

    @AfterClass
    public static void tearDownServer() {
        server.stopServer();
    }

    @Before
    public void setUp() {
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        String pem = pemFrom(keyPair.getPublic());
        // build a sharer
        sharer = Sharer.newBuilder()
                .id(new DeRecIdentity("Incremental Inge", "mailto:test@example.org", null, pem))
                .keyPair(keyPair)
                .notificationListener(Notifier::logNotification)
                .build();
        secret1 = sharer.newSecret("my first secret",
                "quite hush hush".getBytes(StandardCharsets.UTF_8),
                TestIds.INSTANCE.defaultIds);
        secret1.update("an updated version".getBytes(StandardCharsets.UTF_8));
        secret2 = sharer.newSecret("my second secret",
                "very hush hush".getBytes(StandardCharsets.UTF_8),
                TestIds.INSTANCE.defaultIds);
    }

    @After
    public void tearDown() {
        secret1.close();
        secret2.close();
    }


    @Test
    public void listVersionNumbersForHelper() {
    }

    @Test
    public void listHelpersAndSecretsForSharer() {
    }

    @Test
    public void listHelpersAndSecretsForSharer2() {
    }
}