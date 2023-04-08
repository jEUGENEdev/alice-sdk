package org.jeugenedev.alice.core.entity.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Card {
    private String type, title, description;
    @JsonProperty("image_id") private String imageId;
    private Button button;
    private Header header;
    private List<Card> items;
    private Footer footer;

    public Card() {}

    public Card(String type, String title, String description, String imageId, Button button, Header header, List<Card> items, Footer footer) {
        this.type = type;
        this.title = title;
        this.description = description;
        this.imageId = imageId;
        this.button = button;
        this.header = header;
        this.items = items;
        this.footer = footer;
    }

    public String getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getImageId() {
        return imageId;
    }

    public Button getButton() {
        return button;
    }

    public Header getHeader() {
        return header;
    }

    public List<Card> getItems() {
        return items;
    }

    public Footer getFooter() {
        return footer;
    }

    @Override
    public String toString() {
        return "Card{" +
                "type='" + type + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", imageId='" + imageId + '\'' +
                ", button=" + button +
                ", header=" + header +
                ", items=" + items +
                ", footer=" + footer +
                '}';
    }

    static class Header {
        private String text;

        public Header(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }

        @Override
        public String toString() {
            return "Header{" +
                    "text='" + text + '\'' +
                    '}';
        }
    }

    static class Footer {
        private String text;
        private Button button;

        public Footer() {
        }

        public Footer(String text, Button button) {
            this.text = text;
            this.button = button;
        }

        public String getText() {
            return text;
        }

        public Button getButton() {
            return button;
        }

        @Override
        public String toString() {
            return "Footer{" +
                    "text='" + text + '\'' +
                    ", button=" + button +
                    '}';
        }
    }
}
