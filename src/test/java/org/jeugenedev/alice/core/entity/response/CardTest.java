package org.jeugenedev.alice.core.entity.response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CardTest {
    private final ObjectMapper objectMapper = new ObjectMapper();

    private Card getCard(String json) throws JsonProcessingException {
        return objectMapper.readValue(json, Card.class);
    }

    @Test
    public void testBigImage() throws JsonProcessingException {
        Card card = getCard("{\"type\": \"BigImage\",\"image_id\": \"1027858/46r960da47f60207e924\",\"title\": \"Заголовок для изображения\",\"description\": \"Описание изображения.\",\"button\": {\"text\": \"Надпись на кнопке\",\"url\": \"http://example.com/\",\"payload\": {}}}");
        Assertions.assertArrayEquals(
                new String[] {
                        "BigImage",
                        "1027858/46r960da47f60207e924",
                        "Заголовок для изображения",
                        "Описание изображения.",
                        "Надпись на кнопке",
                        "http://example.com/",
                        "{}"
                },
                new String[] {
                        card.getType(),
                        card.getImageId(),
                        card.getTitle(),
                        card.getDescription(),
                        card.getButton().getText(),
                        card.getButton().getUrl(),
                        card.getButton().getPayload()
                }
        );
    }

    @Test
    public void testItemsList() throws JsonProcessingException {
        Card card = getCard("{\"type\": \"ItemsList\",\"header\": {\"text\": \"Заголовок списка изображений\"},\"items\": [{\"image_id\": \"<image_id>\",\"title\": \"Заголовок для изображения.\",\"description\": \"Описание изображения.\",\"button\": {\"text\": \"Надпись на кнопке\",\"url\": \"https://example.com/\",\"payload\": {}}}],\"footer\": {\"text\": \"Текст блока под изображением.\",\"button\": {\"text\": \"Надпись на кнопке\",\"url\": \"https://example.com/\",\"payload\": {}}}}");
        Assertions.assertArrayEquals(
                new String[] {
                        "ItemsList",
                        "Заголовок списка изображений",
                        "<image_id>",
                        "Заголовок для изображения.",
                        "Описание изображения.",
                        "Надпись на кнопке",
                        "https://example.com/",
                        "{}",
                        "Текст блока под изображением.",
                        "Надпись на кнопке",
                        "https://example.com/",
                        "{}"
                },
                new String[] {
                        card.getType(),
                        card.getHeader().getText(),
                        card.getItems().get(0).getImageId(),
                        card.getItems().get(0).getTitle(),
                        card.getItems().get(0).getDescription(),
                        card.getItems().get(0).getButton().getText(),
                        card.getItems().get(0).getButton().getUrl(),
                        card.getItems().get(0).getButton().getPayload(),
                        card.getFooter().getText(),
                        card.getFooter().getButton().getText(),
                        card.getFooter().getButton().getUrl(),
                        card.getFooter().getButton().getPayload()
                }
        );
    }

    @Test
    public void testImageGallery() throws JsonProcessingException {
        Card card = getCard("{\"type\": \"ImageGallery\",\"items\": [{\"image_id\": \"1030496/2769eea171ad1d7bbbfa\",\"title\": \"Картинка 1\",\"button\": {\"text\": \"Надпись на кнопке\",\"url\": \"https://example.com/\",\"payload\": {}}},{\"image_id\": \"1521360/ac3f78abed55b67789d2\",\"title\": \"Картинка 2\",\"button\": {\"text\": \"Надпись на кнопке\",\"url\": \"https://example.com/\",\"payload\": {}}}]}");
        Assertions.assertArrayEquals(
                new String[] {
                        "ImageGallery",
                        "1030496/2769eea171ad1d7bbbfa",
                        "Картинка 1",
                        "Надпись на кнопке",
                        "https://example.com/",
                        "{}",
                        "1521360/ac3f78abed55b67789d2",
                        "Картинка 2",
                        "Надпись на кнопке",
                        "https://example.com/",
                        "{}",
                },
                new String[] {
                        card.getType(),
                        card.getItems().get(0).getImageId(),
                        card.getItems().get(0).getTitle(),
                        card.getItems().get(0).getButton().getText(),
                        card.getItems().get(0).getButton().getUrl(),
                        card.getItems().get(0).getButton().getPayload(),
                        card.getItems().get(1).getImageId(),
                        card.getItems().get(1).getTitle(),
                        card.getItems().get(1).getButton().getText(),
                        card.getItems().get(1).getButton().getUrl(),
                        card.getItems().get(1).getButton().getPayload()
                }
        );
    }
}
