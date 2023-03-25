package org.jeugenedev.alice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

public class AliceTest {
    private final int RESERVED_SERVER_PORT = 55855;

    @Test
    public void aliceServerShouldRespondToPing() throws IOException, InterruptedException {
        Alice alice = Alice.getInstance(RESERVED_SERVER_PORT);
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
        alice.stop(0);
    }

    @Test
    public void onStartAndOnStopShouldDependAndWorkOnChangingServerState() throws IOException {
        Alice alice = Alice.getInstance(RESERVED_SERVER_PORT);
        AtomicInteger thisData = new AtomicInteger(10);
        AtomicInteger postData = new AtomicInteger(5);
        alice.setOnStartListener(server -> {
            Assertions.assertEquals(10, thisData.get());
            thisData.set(postData.get());
            server.stop(10);
        });
        alice.setOnStopListener(server -> Assertions.assertEquals(postData.get(), thisData.get()));
    }
}
