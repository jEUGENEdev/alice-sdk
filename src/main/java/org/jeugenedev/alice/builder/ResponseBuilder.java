package org.jeugenedev.alice.builder;

import org.jeugenedev.alice.core.entity.response.Response;

public final class ResponseBuilder extends Returning<ServerResponseBuilder>
        implements Builder.RequiredResponseItemBuilder, Builder.TextAndTtsResponseItemBuilder, Builder.EndSessionResponseItemBuilder, Builder.PostResponseItemBuilder, Builder.Assembled<Response> {
    private Response response;
    private String text, tts;
    private boolean endSession;

    ResponseBuilder(ServerResponseBuilder prev) {
        super(prev);
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
    public Builder.PostResponseItemBuilder setEndSession(boolean end) {
        this.endSession = end;
        return this;
    }

    @Override
    public Response assemble() {
        return response;
    }

    @Override
    public Builder.PostResponseItemBuilder buttons() {
        return null;
    }

    @Override
    public Builder.PostResponseItemBuilder itemMeta() {
        return null;
    }

    @Override
    public Builder.PostResponseItemBuilder card() {
        return null;
    }

    @Override
    public Builder.PostResponseItemBuilder directives() {
        return null;
    }
}
