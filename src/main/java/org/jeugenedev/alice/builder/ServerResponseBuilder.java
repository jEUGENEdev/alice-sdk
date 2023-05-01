package org.jeugenedev.alice.builder;

import org.jeugenedev.alice.core.server.response.ServerResponse;

public final class ServerResponseBuilder
        implements Builder.SaveStatesResponseBuilder, Builder.PostServerResponseBuilder, Builder.Assembled<ServerResponse> {
    private String version;
    private boolean session, user, application;
    private ResponseBuilder response = new ResponseBuilder(this);

    private ServerResponseBuilder(String version) {
        this.version = version;
    }

    public static Builder.SaveStatesResponseBuilder of(Version version) {
        return new ServerResponseBuilder(version.getVersion());
    }

    public static Builder.SaveStatesResponseBuilder ofDefault() {
        return new ServerResponseBuilder(Version.VERSION_1_0.getVersion());
    }

    @Override
    public Builder.RequiredResponseItemBuilder save(boolean session, boolean user, boolean application) {
        this.session = session;
        this.user = user;
        this.application = application;
        return this.response;
    }

    @Override
    public ServerResponse assemble() {
        return new ServerResponse(version, response.assemble(), null, null, null, null);
    }

    public enum Version {
        VERSION_1_0("1.0");

        private final String version;

        Version(String version) {
            this.version = version;
        }

        public String getVersion() {
            return version;
        }
    }
}
