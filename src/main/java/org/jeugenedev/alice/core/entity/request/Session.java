package org.jeugenedev.alice.core.entity.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Session {
    @JsonProperty("message_id") private long messageId;
    @JsonProperty("session_id") private String sessionId;
    @JsonProperty("skill_id") private String skillId;
    @JsonProperty("user_id") private String userId;
    @JsonProperty("new") private boolean newSession;
    private User user;
    private Application application;

    public Session() {}

    public Session(long messageId, String sessionId, String skillId, String userId, boolean newSession) {
        this.messageId = messageId;
        this.sessionId = sessionId;
        this.skillId = skillId;
        this.userId = userId;
        this.newSession = newSession;
    }

    public long getMessageId() {
        return messageId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public String getSkillId() {
        return skillId;
    }

    public String getUserId() {
        return userId;
    }

    public boolean isNewSession() {
        return newSession;
    }

    public User getUser() {
        return user;
    }

    public Application getApplication() {
        return application;
    }

    @Override
    public String toString() {
        return "Session{" +
                "messageId=" + messageId +
                ", sessionId='" + sessionId + '\'' +
                ", skillId='" + skillId + '\'' +
                ", userId='" + userId + '\'' +
                ", newSession=" + newSession +
                ", user=" + user +
                ", application=" + application +
                '}';
    }

    static class User {
        @JsonProperty("user_id") private String userId;
        @JsonProperty("access_token") private String accessToken;

        public User() {}

        public User(String userId, String accessToken) {
            this.userId = userId;
            this.accessToken = accessToken;
        }

        public String getUserId() {
            return userId;
        }

        public String getAccessToken() {
            return accessToken;
        }
    }

    static class Application {
        @JsonProperty("application_id") private String applicationId;

        public Application() {}

        public Application(String applicationId) {
            this.applicationId = applicationId;
        }

        public String getApplicationId() {
            return applicationId;
        }
    }
}
