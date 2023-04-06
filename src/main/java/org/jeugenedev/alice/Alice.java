package org.jeugenedev.alice;

import ch.qos.logback.classic.Logger;
import com.sun.net.httpserver.HttpServer;
import org.apache.commons.net.util.SubnetUtils;
import org.jeugenedev.alice.core.server.Initializer;
import org.jeugenedev.alice.exception.NoServerException;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
    private final Logger serverLogger = (Logger) LoggerFactory.getLogger("Alice Server");
    private boolean onlyYandex;
    private List<String> remoteIps;

    private static Alice instance;

    public static Alice getInstance() {
        if(instance == null) {
            throw new NoServerException();
        }
        return instance;
    }

    public static Alice getInstance(int serverPort) throws IOException {
        if(instance == null) {
            instance = new Alice(serverPort);
        }
        return instance;
    }

    public static boolean isInit() {
        return instance != null;
    }

    private Alice(int serverPort) throws IOException {
        this.getServerLogger().info("""
                Initialization Alice Server
                █▀▀▄ █▀▀ ▀█─█▀ ── █▀▀ █──█ █▀▀▀ █▀▀ █▀▀▄ █▀▀ ─ █▀▀█ █──█\s
                █──█ █▀▀ ─█▄█─ ▀▀ █▀▀ █──█ █─▀█ █▀▀ █──█ █▀▀ ▄ █▄▄▀ █──█\s
                ▀▀▀─ ▀▀▀ ──▀── ── ▀▀▀ ─▀▀▀ ▀▀▀▀ ▀▀▀ ▀──▀ ▀▀▀ █ ▀─▀▀ ─▀▀▀
                ░█████╗░██╗░░░░░██╗░█████╗░███████╗░░░░░░░██████╗██████╗░██╗░░██╗
                ██╔══██╗██║░░░░░██║██╔══██╗██╔════╝░░░░░░██╔════╝██╔══██╗██║░██╔╝
                ███████║██║░░░░░██║██║░░╚═╝█████╗░░█████╗╚█████╗░██║░░██║█████═╝░
                ██╔══██║██║░░░░░██║██║░░██╗██╔══╝░░╚════╝░╚═══██╗██║░░██║██╔═██╗░
                ██║░░██║███████╗██║╚█████╔╝███████╗░░░░░░██████╔╝██████╔╝██║░╚██╗
                ╚═╝░░╚═╝╚══════╝╚═╝░╚════╝░╚══════╝░░░░░░╚═════╝░╚═════╝░╚═╝░░╚═╝""");
        this.server = HttpServer.create(new InetSocketAddress("127.0.0.1", serverPort), 0);
        Initializer.addDefaultContext(this.server);
        Initializer.registerListenRequest(this.server);
    }

    public void start() {
        server.start();
        getServerLogger().info("Alice Server has been started.");
        this.onStartListener.start(this.server);
        getServerLogger().info("The onstart event was executed.");
    }

    public void stop(int delay) {
        server.stop(delay);
        getServerLogger().info("Alice Server has been stopped.");
        this.onStopListener.stop(this.server);
    }

    public void setOnStartListener(OnStartListener onStartListener) {
        this.onStartListener = onStartListener;
    }

    public void setOnStopListener(OnStopListener onStopListener) {
        this.onStopListener = onStopListener;
    }

    public Logger getServerLogger() {
        return serverLogger;
    }

    public HttpServer getServer() {
        return server;
    }

    public boolean isYandexIp(String ip) {
        if(this.remoteIps == null) {
            this.remoteIps = new ArrayList<>();
            try(Scanner scanner = new Scanner(getClass().getClassLoader().getResourceAsStream("/yandex.ips"))) {
                while(scanner.hasNextLine()) {
                    this.remoteIps.add(scanner.nextLine());
                }
            }
        }
        for(String addr : this.remoteIps) {
            SubnetUtils subnet = new SubnetUtils(addr);
            if(subnet.getInfo().isInRange(ip)) return true;
        }
        return false;
    }

    public Alice onlyYandexServers() {
        // https://yandex.ru/ips
        this.onlyYandex = true;
        return this;
    }

    public boolean isOnlyYandex() {
        return this.onlyYandex;
    }

    interface OnStartListener {
        void start(HttpServer server);
    }

    interface OnStopListener {
        void stop(HttpServer server);
    }
}
