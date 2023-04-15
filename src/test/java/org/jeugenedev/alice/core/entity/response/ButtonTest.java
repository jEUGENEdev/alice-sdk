package org.jeugenedev.alice.core.entity.response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ButtonTest {
    @Test
    public void test1() throws JsonProcessingException {
        String json = "{\"title\": \"Надпись на кнопке\",\"payload\": {},\"url\": \"https://example.com/\",\"hide\": true}";
        Button button = new ObjectMapper().readValue(json, Button.class);
        Assertions.assertArrayEquals(
                new String[] {
                        "Надпись на кнопке",
                        "{}",
                        "https://example.com/",
                        "true",
                        null
                },
                new String[] {
                        button.getTitle(),
                        button.getPayload(),
                        button.getUrl(),
                        button.isHide() + "",
                        button.getText()
                }
        );
    }

    @Test
    public void test2() throws JsonProcessingException {
        String json = "{\"text\": \"Надпись на кнопке\",\"url\": \"http://example.com/\",\"payload\": {}}";
        Button button = new ObjectMapper().readValue(json, Button.class);
        Assertions.assertArrayEquals(
                new String[] {
                        "Надпись на кнопке",
                        "http://example.com/",
                        "{}",
                        "false",
                        null
                },
                new String[] {
                        button.getText(),
                        button.getUrl(),
                        button.getPayload(),
                        button.isHide() + "",
                        button.getTitle()
                }
        );
    }
}
