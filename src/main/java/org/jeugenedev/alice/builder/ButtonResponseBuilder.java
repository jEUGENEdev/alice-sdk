package org.jeugenedev.alice.builder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jeugenedev.alice.core.entity.response.Button;

public final class ButtonResponseBuilder extends Returning<Builder.PostResponseItemBuilder>
        implements Builder.ButtonResponseItemBuilder, Builder.HideButtonResponseItemBuilder, Builder.PostButtonResponseItemBuilder, Builder.Assembled<Button> {
    private String title, url;
    private JsonNode payload;
    private boolean hide;

    public static Builder.ButtonResponseItemBuilder of() {
        return new ButtonResponseBuilder(null);
    }

    private ObjectMapper objectMapper = new ObjectMapper();

    {
        try {
            payload = objectMapper.readTree("{}");
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    ButtonResponseBuilder(Builder.PostResponseItemBuilder prev) {
        super(prev);
    }

    @Override
    public Builder.PostResponseItemBuilder postResponseItemBuilder() {
        ((ResponseBuilder) super.back()).getButtons().add(assemble());
        return back();
    }

    @Override
    public Builder.HideButtonResponseItemBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    @Override
    public Builder.PostButtonResponseItemBuilder setHide(boolean hide) {
        this.hide = hide;
        return this;
    }

    @Override
    public Builder.PostButtonResponseItemBuilder setUrl(String url) {
        this.url = url;
        return this;
    }

    @Override
    public Builder.PostButtonResponseItemBuilder setPayload(String json) {
        try {
            this.payload = objectMapper.readTree(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return this;
    }

    @Override
    public Button assemble() {
        return new Button(title, url, null, hide, payload);
    }
}
