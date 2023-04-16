package org.jeugenedev.alice.core.entity.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Response {
    private String text, tts;
    private List<Button> buttons;
    @JsonProperty("end_session") private boolean endSession;
    @JsonProperty("show_item_meta") private ItemMeta itemMeta;
    private Card card;
    private Directive directives;

    public Response() {
    }

    public Response(String text, String tts, List<Button> buttons, boolean endSession, ItemMeta itemMeta, Card card, Directive directives) {
        this.text = text;
        this.tts = tts;
        this.buttons = buttons;
        this.endSession = endSession;
        this.itemMeta = itemMeta;
        this.card = card;
        this.directives = directives;
    }

    public String getText() {
        return text;
    }

    public String getTts() {
        return tts;
    }

    public List<Button> getButtons() {
        return buttons;
    }

    public boolean isEndSession() {
        return endSession;
    }

    public ItemMeta getItemMeta() {
        return itemMeta;
    }

    public Directive getDirectives() {
        return directives;
    }

    public Card getCard() {
        return card;
    }

    @Override
    public String toString() {
        return "Response{" +
                "text='" + text + '\'' +
                ", tts='" + tts + '\'' +
                ", buttons=" + buttons +
                ", endSession=" + endSession +
                ", itemMeta=" + itemMeta +
                ", card=" + card +
                ", directives=" + directives +
                '}';
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
