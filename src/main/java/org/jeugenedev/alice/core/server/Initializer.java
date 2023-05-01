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

import java.io.PrintWriter;
import java.io.StringWriter;
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

    private static void need(HttpServer server, Alice alice, HttpExchange exchange, String data) {
        if(alice.isOnlyYandex() && alice.isYandexIp(exchange.getRemoteAddress().getHostName())) throw new AliceDeniedException();
        if(alice.isLoggingRequests()) alice.getServerLogger().info(String.join(": ", exchange.getRemoteAddress().getHostName(), data));
    }

    public static void registerListenRequest(HttpServer server) {
        server.createContext("/", exchange -> {
            try(Scanner input = new Scanner(exchange.getRequestBody(), StandardCharsets.UTF_8)) {
                String data = input.useDelimiter("\\A").next();
                need(server, Alice.getInstance(), exchange, data);
                Request request = RequestManager.getRequestListener();
                ServerRequest serverRequest = requestMapper.readValue(data, ServerRequest.class);
                ServerResponse serverResponse = request.request(serverRequest);
                byte[] response = responseMapper.writeValueAsString(serverResponse).getBytes(StandardCharsets.UTF_8);
                exchange.getResponseHeaders().set("Content-Type", "application/json; charset=UTF-8");
                exchange.sendResponseHeaders(200, response.length);
                exchange.getResponseBody().write(response);
            } catch(NoSuchElementException e) {
                exchange.sendResponseHeaders(400, -1);
                throw new RuntimeException(e);
            } catch(AliceDeniedException e1) {
                exchange.sendResponseHeaders(403, -1);
                throw new RuntimeException(e1);
            } catch(Exception main) {
                exchange.sendResponseHeaders(500, -1);
                StringWriter writer = new StringWriter();
                PrintWriter printWriter = new PrintWriter(writer);
                main.printStackTrace(printWriter);
                Alice.getInstance().getServerLogger().error(writer.toString());
                printWriter.close();
            }
        });
    }
}
