package org.jeugenedev.alice.core.server.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import org.jeugenedev.alice.core.entity.response.Analytics;
import org.jeugenedev.alice.core.entity.response.Response;

public class ServerResponse {
    private String version;
    private Response response;
    private Analytics analytics;
    @JsonProperty("session_state") private State sessionState;
    @JsonProperty("user_state_update") private State userState;
    @JsonProperty("application_state") private State applicationState;

    public ServerResponse() {
    }

    public ServerResponse(String version, Response response, Analytics analytics, State sessionState, State userState, State applicationState) {
        this.version = version;
        this.response = response;
        this.analytics = analytics;
        this.sessionState = sessionState;
        this.userState = userState;
        this.applicationState = applicationState;
    }

    public String getVersion() {
        return version;
    }

    public Response getResponse() {
        return response;
    }

    public Analytics getAnalytics() {
        return analytics;
    }

    public State getSessionState() {
        return sessionState;
    }

    public State getUserState() {
        return userState;
    }

    public State getApplicationState() {
        return applicationState;
    }

    static class State {
        private String name;
        private JsonNode value;

        public State() {
        }

        public State(String name, JsonNode value) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public JsonNode getValue() {
            return value;
        }

        @Override
        public String toString() {
            return "State{" +
                    "name='" + name + '\'' +
                    ", value=" + value +
                    '}';
        }
    }
}
