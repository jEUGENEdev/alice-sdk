package org.jeugenedev.alice.builder;

import org.jeugenedev.alice.core.entity.response.Button;
import org.jeugenedev.alice.core.entity.response.Response;

import java.util.ArrayList;
import java.util.List;

public final class ResponseBuilder extends Returning<Builder.PostServerResponseBuilder>
        implements Builder.RequiredResponseItemBuilder, Builder.TextAndTtsResponseItemBuilder, Builder.EndSessionResponseItemBuilder, Builder.PostResponseItemBuilder, Builder.Assembled<Response> {
    private String text, tts;
    private boolean endSession;
    private List<Button> buttons = new ArrayList<>();

    ResponseBuilder(Builder.PostServerResponseBuilder prev) {
        super(prev);
    }

    List<Button> getButtons() {
        return this.buttons;
    }

    @Override
    public Builder.TextAndTtsResponseItemBuilder getRequiredResponseBuilder() {
        return this;
    }

    @Override
    public Builder.EndSessionResponseItemBuilder setData(String text, String tts) {
        this.text = text;
        this.tts = tts;
        return this;
    }

    @Override
    public Builder.EndSessionResponseItemBuilder setData(String text) {
        this.text = text;
        this.tts = text;
        return this;
    }

    @Override
    public Builder.PostResponseItemBuilder setEndSession(boolean end) {
        this.endSession = end;
        return this;
    }

    @Override
    public Response assemble() {
        return new Response(text, tts, buttons, endSession, null, null, null);
    }

    @Override
    public Builder.ButtonResponseItemBuilder addButton() {
        return new ButtonResponseBuilder(this);
    }

    @Override
    public Builder.PostResponseItemBuilder addButtons(List<Button> buttons) {
        getButtons().addAll(buttons);
        return this;
    }

    @Override
    public Builder.ItemMediaResponseItemBuilder itemMediaBuilder() {
        return null;
    }

    @Override
    public Builder.CardResponseItemBuilder cardBuilder() {
        return null;
    }

    @Override
    public Builder.DirectivesResponseItemBuilder directivesBuilder() {
        return null;
    }
}
