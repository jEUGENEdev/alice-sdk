package org.jeugenedev.alice;

public final class RequestManager {
    private static Request requestListener = request -> null;

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
