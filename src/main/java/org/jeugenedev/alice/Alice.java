package org.jeugenedev.alice;

import com.sun.net.httpserver.HttpServer;
import org.jeugenedev.alice.core.server.Initializer;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * ВАЖНО! Listeners на остановку и запуск сервера должны срабатывать после соответствующего события!
 * Классы, реализующие интерфейсы Listeners не должны быть null
 * OnStopListener - callback, срабатывающий ПОСЛЕ остановки сервера
 * OnStopListener - callback, срабатывающий ПОСЛЕ запуска сервера
 */
public final class Alice {
    private final HttpServer server;
    private OnStartListener onStartListener = server -> {};
    private OnStopListener onStopListener = server -> {};

    private static Alice instance;

    public static Alice getInstance(int serverPort) throws IOException {
        if(instance == null) {
            instance = new Alice(serverPort);
        }
        return instance;
    }

    private Alice(int serverPort) throws IOException {
        this.server = HttpServer.create(new InetSocketAddress("127.0.0.1", serverPort), 0);
        Initializer.addDefaultContext(this.server);
    }

    public void start() {
        server.start();
        this.onStartListener.start(this.server);
    }

    public void stop(int delay) {
        server.stop(delay);
        this.onStopListener.stop(this.server);
    }

    public void setOnStartListener(OnStartListener onStartListener) {
        this.onStartListener = onStartListener;
    }

    public void setOnStopListener(OnStopListener onStopListener) {
        this.onStopListener = onStopListener;
    }

    interface OnStartListener {
        void start(HttpServer server);
    }

    interface OnStopListener {
        void stop(HttpServer server);
    }
}
