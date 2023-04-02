package org.jeugenedev.alice.core.server.request;

import org.jeugenedev.alice.core.entity.request.*;

public class ServerRequest implements Listenable {
    private Meta meta;
    private Request request;
    private Session session;
    private State state;
    private String version;

    public ServerRequest() {}

    public Meta getMeta() {
        return meta;
    }

    public Request getRequest() {
        return request;
    }

    public Session getSession() {
        return session;
    }

    public State getState() {
        return state;
    }

    public String getVersion() {
        return version;
    }

    @Override
    public ServerRequest listen() {
        return null;
    }
}
