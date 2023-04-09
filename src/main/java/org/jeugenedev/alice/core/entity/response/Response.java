package org.jeugenedev.alice.core.entity.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;

public class Response {
    private String text, tts, version;
    private List<Button> buttons;
    @JsonProperty("end_session") private boolean endSession;
    private Analytics analytics;
    @JsonProperty("session_state") private State sessionState;
    @JsonProperty("user_state_update") private State userState;
    @JsonProperty("application_state") private State applicationState;
    @JsonProperty("show_item_meta") private ItemMeta itemMeta;

    public Response() {
    }

    public Response(String text, String tts, String version, List<Button> buttons, boolean endSession, Analytics analytics, State sessionState, State userState, State applicationState, ItemMeta itemMeta) {
        this.text = text;
        this.tts = tts;
        this.version = version;
        this.buttons = buttons;
        this.endSession = endSession;
        this.analytics = analytics;
        this.sessionState = sessionState;
        this.userState = userState;
        this.applicationState = applicationState;
        this.itemMeta = itemMeta;
    }

    public String getText() {
        return text;
    }

    public String getTts() {
        return tts;
    }

    public String getVersion() {
        return version;
    }

    public List<Button> getButtons() {
        return buttons;
    }

    public boolean isEndSession() {
        return endSession;
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

    public ItemMeta getItemMeta() {
        return itemMeta;
    }

    @Override
    public String toString() {
        return "Response{" +
                "text='" + text + '\'' +
                ", tts='" + tts + '\'' +
                ", version='" + version + '\'' +
                ", buttons=" + buttons +
                ", endSession=" + endSession +
                ", analytics=" + analytics +
                ", sessionState=" + sessionState +
                ", userState=" + userState +
                ", applicationState=" + applicationState +
                ", itemMeta=" + itemMeta +
                '}';
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

    static class ItemMeta {
        private String title;
        @JsonProperty("content_id") private String contentId;
        @JsonProperty("title_tts") private String titleTts;
        @JsonProperty("publication_date") private String publicationDate;
        @JsonProperty("expiration_date") private String expirationDate;

        public ItemMeta() {
        }

        public ItemMeta(String title, String contentId, String titleTts, String publicationDate, String expirationDate) {
            this.title = title;
            this.contentId = contentId;
            this.titleTts = titleTts;
            this.publicationDate = publicationDate;
            this.expirationDate = expirationDate;
        }

        public String getTitle() {
            return title;
        }

        public String getContentId() {
            return contentId;
        }

        public String getTitleTts() {
            return titleTts;
        }

        public String getPublicationDate() {
            return publicationDate;
        }

        public String getExpirationDate() {
            return expirationDate;
        }

        @Override
        public String toString() {
            return "ItemMeta{" +
                    "title='" + title + '\'' +
                    ", contentId='" + contentId + '\'' +
                    ", titleTts='" + titleTts + '\'' +
                    ", publicationDate='" + publicationDate + '\'' +
                    ", expirationDate='" + expirationDate + '\'' +
                    '}';
        }
    }
}
