package org.jeugenedev.alice.core.server;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import org.jeugenedev.alice.Alice;
import org.jeugenedev.alice.Request;
import org.jeugenedev.alice.RequestManager;
import org.jeugenedev.alice.core.server.request.ServerRequest;
import org.jeugenedev.alice.core.server.response.ServerResponse;
import org.jeugenedev.alice.exception.AliceDeniedException;

import java.nio.charset.StandardCharsets;
import java.util.NoSuchElementException;
import java.util.Scanner;

public final class Initializer {
    private static final ObjectMapper requestMapper = new ObjectMapper();
    private static final ObjectMapper responseMapper = new ObjectMapper();

    static {
        requestMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        responseMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    }

    private Initializer() {}

    public static void addDefaultContext(HttpServer server) {
        server.createContext("/p/i/n/g", (exchange) -> {
            String r = "OK";
            exchange.setAttribute("Content-Type", "text/text");
            exchange.sendResponseHeaders(200, r.length());
            exchange.getResponseBody().write(r.getBytes(StandardCharsets.UTF_8));
            exchange.close();
        });
    }

    private static void need(HttpServer server, Alice alice, HttpExchange exchange) {
        if(alice.isOnlyYandex() && alice.isYandexIp(exchange.getRemoteAddress().getHostName())) throw new AliceDeniedException();
    }

    public static void registerListenRequest(HttpServer server) {
        server.createContext("/", exchange -> {
            try(Scanner input = new Scanner(exchange.getRequestBody(), StandardCharsets.UTF_8)) {
                need(server, Alice.getInstance(), exchange);
                String data = input.useDelimiter("\\A").next();
                Request request = RequestManager.getRequestListener();
                ServerRequest serverRequest = requestMapper.readValue(data, ServerRequest.class);
                ServerResponse serverResponse = request.request(serverRequest);
                String response = responseMapper.writeValueAsString(serverResponse);
                exchange.setAttribute("Content-Type", "application/json");
                exchange.sendResponseHeaders(200, response.length());
                exchange.getResponseBody().write(response.getBytes(StandardCharsets.UTF_8));
            } catch(NoSuchElementException e) {
                exchange.sendResponseHeaders(400, -1);
                throw new RuntimeException(e);
            } catch(AliceDeniedException e1) {
                exchange.sendResponseHeaders(403, -1);
                throw new RuntimeException(e1);
            } catch(Exception main) {
                throw new RuntimeException(main);
            }
        });
    }
}
