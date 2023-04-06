package org.jeugenedev.alice.core.server.request;

import org.jeugenedev.alice.core.entity.request.*;

public class ServerRequest {
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
    public String toString() {
        return "ServerRequest{" +
                "meta=" + meta +
                ", request=" + request +
                ", session=" + session +
                ", state=" + state +
                ", version='" + version + '\'' +
                '}';
    }
}
