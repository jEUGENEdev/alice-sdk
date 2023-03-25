package org.jeugenedev.alice.core.server;

import com.sun.net.httpserver.HttpServer;

import java.nio.charset.StandardCharsets;

public final class Initializer {
    private Initializer() {}

    public static void addDefaultContext(HttpServer server) {
        server.createContext("/p/i/n/g", (h) -> {
            String r = "OK";
            h.setAttribute("Content-Type", "text/text");
            h.sendResponseHeaders(200, r.length());
            h.getResponseBody().write(r.getBytes(StandardCharsets.UTF_8));
            h.close();
        });
    }
}
