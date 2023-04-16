package org.jeugenedev.alice.core.entity.response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ResponseTest {
    @Test
    public void test() throws JsonProcessingException {
        String json = "{\"text\":\"Здравствуйте! Это мы, хороводоведы.\",\"tts\":\"Здравствуйте! Это мы, хоров+одо в+еды.\",\"card\":{\"type\":\"BigImage\",\"image_id\":\"1027858/46r960da47f60207e924\",\"title\":\"Заголовок для изображения\",\"description\":\"Описание изображения.\",\"button\":{\"text\":\"Надпись на кнопке\",\"url\":\"http://example.com/\",\"payload\":{}}},\"buttons\":[{\"title\":\"Надпись на кнопке\",\"payload\":{},\"url\":\"https://example.com/\",\"hide\":true}],\"end_session\":false,\"directives\":{\"audio_player\":{\"action\":\"Play\",\"item\":{\"stream\":{\"url\":\"https://example.com/stream-audio-url\",\"offset_ms\":0,\"token\":\"token\"},\"metadata\":{\"title\":\"Песня\",\"sub_title\":\"Артист\",\"art\":{\"url\":\"https://example.com/art.png\"},\"background_image\":{\"url\":\"https://example.com/background-image.png\"}}}}},\"show_item_meta\":{\"content_id\":\"2870a69a-0eaa-43c0-8900-8afa7c36c127\",\"title\":\"story title\",\"title_tts\":\"story title tts\",\"publication_date\":\"2020-12-03T10:39:32.195044179Z\",\"expiration_date\":\"2020-12-03T10:59:32.195044179Z\"}}";
        Response response = new ObjectMapper().readValue(json, Response.class);
        Assertions.assertArrayEquals(
                new String[] {
                        "Здравствуйте! Это мы, хороводоведы.",
                        "Здравствуйте! Это мы, хоров+одо в+еды.",
                        "true",
                        "Надпись на кнопке",
                        "https://example.com/",
                        "{}",
                        "true",
                        "false",
                        "true",
                        "2870a69a-0eaa-43c0-8900-8afa7c36c127",
                        "story title",
                        "story title tts",
                        "2020-12-03T10:39:32.195044179Z",
                        "2020-12-03T10:59:32.195044179Z"
                },
                new String[] {
                        response.getText(),
                        response.getTts(),
                        (response.getCard() != null) + "",
                        response.getButtons().get(0).getTitle(),
                        response.getButtons().get(0).getUrl(),
                        response.getButtons().get(0).getPayload(),
                        response.getButtons().get(0).isHide() + "",
                        response.isEndSession() + "",
                        (response.getDirectives() != null) + "",
                        response.getItemMeta().getContentId(),
                        response.getItemMeta().getTitle(),
                        response.getItemMeta().getTitleTts(),
                        response.getItemMeta().getPublicationDate(),
                        response.getItemMeta().getExpirationDate()
                }
        );
    }
}
