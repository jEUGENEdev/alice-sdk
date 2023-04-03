package org.jeugenedev.alice;

import org.jeugenedev.alice.exception.NoServerException;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AliceTest {
    private final int RESERVED_SERVER_PORT = 55855;

    @Test
    @Order(1)
    public void serverShouldThrowException() {
        Assertions.assertThrows(NoServerException.class, Alice::getInstance);
    }

    @Test
    @Order(2)
    public void serverShouldNotBeInitialized() {
        Assertions.assertFalse(Alice.isInit());
    }

    @Test
    @Order(3)
    public void aliceServerShouldRespondToPingAndonStartAndOnStopShouldDependAndWorkOnChangingServerState() throws IOException, InterruptedException {
        Alice alice = Alice.getInstance(RESERVED_SERVER_PORT);
        AtomicInteger thisData = new AtomicInteger(10);
        AtomicInteger postData = new AtomicInteger(5);
        alice.setOnStartListener(server -> {
            Assertions.assertEquals(10, thisData.get());
            thisData.set(postData.get());
        });
        alice.setOnStopListener(server -> Assertions.assertEquals(postData.get(), thisData.get()));
        alice.start();
        HttpClient client = HttpClient.newBuilder().build();
        HttpResponse<String> response = client
                .send(HttpRequest
                        .newBuilder(URI.create("http://localhost:" + RESERVED_SERVER_PORT + "/p/i/n/g"))
                        .GET()
                        .timeout(Duration.ofSeconds(1))
                        .build(), HttpResponse.BodyHandlers.ofString()
                );
        Assertions.assertEquals("OK", response.body());
        alice.stop(10);
    }

    @Test
    @Order(4)
    public void serverMustHaveInitializedPort() {
        Alice alice = Alice.getInstance();
        Assertions.assertEquals(RESERVED_SERVER_PORT, alice.getServer().getAddress().getPort());
    }

    @Test
    @Order(5)
    public void serverShouldBeInitialized() {
        Assertions.assertTrue(Alice.isInit());
    }
}
