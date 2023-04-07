package org.jeugenedev.alice.core.entity.response;

import com.fasterxml.jackson.databind.JsonNode;

public class Button {
    private String title, url, text;
    private boolean hide;
    private JsonNode payload;

    public Button() {
    }

    public Button(String title, String url, String text, boolean hide, JsonNode payload) {
        this.title = title;
        this.url = url;
        this.text = text;
        this.hide = hide;
        this.payload = payload;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public boolean isHide() {
        return hide;
    }

    public String getPayload() {
        return payload.toString();
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return "Button{" +
                "title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", text='" + text + '\'' +
                ", hide=" + hide +
                ", payload=" + payload +
                '}';
    }
}
