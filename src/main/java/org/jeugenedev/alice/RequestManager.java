package org.jeugenedev.alice;

import org.jeugenedev.alice.core.server.response.ServerResponse;

public final class RequestManager {
    private static Request requestListener = request -> new ServerResponse();

    private RequestManager() {}

    public static Request getRequestListener() {
        return requestListener;
    }

    public static void addRequestListener(Request request) {
        requestListener = request;
    }

    public static void removeRequestListener(String listenerName) {
        requestListener = serverRequest -> null;
    }
}
