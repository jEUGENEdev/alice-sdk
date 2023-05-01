package org.jeugenedev.alice.builder;

import org.jeugenedev.alice.core.entity.response.Button;
import org.jeugenedev.alice.core.server.response.ServerResponse;

import java.util.List;

/** содержит обязательные (not null) перед отправкой поля ответа */
public final class Builder {
    private Builder() {}

    public interface Assembled<T> {
        /** собирает текущий объект в соответствующий классу объект */
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
        Builder.EndSessionResponseItemBuilder setData(String text);
    }

    public interface EndSessionResponseItemBuilder {
        /** определяет, будет ли сброшена сессия после текущего ответа на запрос Яндекса */
        PostResponseItemBuilder setEndSession(boolean end);
    }

    public interface PostServerResponseBuilder extends Assembled<ServerResponse> {
    }

    public interface PostResponseItemBuilder {
        /** возращает ServerResponseBuilder */
        PostServerResponseBuilder back();
        /** возращает строитель кнопки */
        ButtonResponseItemBuilder addButton();
        PostResponseItemBuilder addButtons(List<Button> buttons);
        /** возращает строитель itemMedia */
        ItemMediaResponseItemBuilder itemMediaBuilder();
        /** возращает строитель cardBuilder */
        CardResponseItemBuilder cardBuilder();
        /** возращает строитель directives */
        DirectivesResponseItemBuilder directivesBuilder();
    }

    public interface GetPostResponseItemBuilder {
        /** возращает PostResponseItemBuilder */
        PostResponseItemBuilder postResponseItemBuilder();
    }

    public interface ButtonResponseItemBuilder {
        /** изменяет текст на кнопке */
        HideButtonResponseItemBuilder setTitle(String text);
    }

    public interface HideButtonResponseItemBuilder {
        PostButtonResponseItemBuilder setHide(boolean hide);
    }

    public interface PostButtonResponseItemBuilder extends GetPostResponseItemBuilder, Assembled<Button> {
        PostButtonResponseItemBuilder setUrl(String url);
        PostButtonResponseItemBuilder setPayload(String json);
    }

    public interface ItemMediaResponseItemBuilder extends GetPostResponseItemBuilder {
    }

    public interface CardResponseItemBuilder extends GetPostResponseItemBuilder {
    }

    public interface DirectivesResponseItemBuilder extends GetPostResponseItemBuilder {
    }
}
