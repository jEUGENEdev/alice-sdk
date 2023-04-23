package org.jeugenedev.alice.builder;

/** содержит обязательные (not null) перед отправкой поля ответа */
public final class Builder {
    private Builder() {}

    public interface Assembled<T> {
        T assemble();
    }

    public interface SaveStatesResponseBuilder {
        /**
         * определяет, будет ли сохраняться (true) состояние для session state, user state, application state.
         * при значении false следующий (через один) запрос от Яндекса НЕ будет содержать state.session,
         * state.user и state.application соответственно.
         */
        Builder.RequiredResponseItemBuilder save(boolean session, boolean user, boolean application);
    }

    public interface RequiredResponseItemBuilder {
        /** возращает новый ResponseBuilder, но с обязательными этапами инициализации */
        TextAndTtsResponseItemBuilder getRequiredResponseBuilder();
    }

    public interface TextAndTtsResponseItemBuilder {
        /** изменяет сообщение и озвучку для сообщения в ответ на запрос Яндекса */
        Builder.EndSessionResponseItemBuilder setData(String text, String tts);
    }

    public interface EndSessionResponseItemBuilder {
        /** определяет, будет ли сброшена сессия после текущего ответа на запрос Яндекса */
        PostResponseItemBuilder setEndSession(boolean end);
    }

    public interface PostResponseItemBuilder {
        /** возращает ServerResponseBuilder */
        ServerResponseBuilder back();
        PostResponseItemBuilder buttons();
        PostResponseItemBuilder itemMeta();
        PostResponseItemBuilder card();
        PostResponseItemBuilder directives();
    }
}
