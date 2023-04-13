package org.jeugenedev.alice.core.entity.response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DirectiveTest {
    private Directive getDirective(String json) throws JsonProcessingException {
        return new ObjectMapper().readValue(json, Directive.class);
    }

    @Test
    public void test1() throws JsonProcessingException {
        Directive directive = getDirective("{\"audio_player\": {\"action\": \"Play\",\"item\": {\"stream\": {\"url\": \"https://example.com/stream-audio-url\",\"offset_ms\": 0,\"token\": \"token\"},\"metadata\": {\"title\": \"Песня\",\"sub_title\": \"Артист\",\"art\": {\"url\": \"https://example.com/art.png\"},\"background_image\": {\"url\": \"https://example.com/background-image.png\"}}}}}");
        Assertions.assertArrayEquals(
                new String[] {
                        "true",
                        "Play",
                        "https://example.com/stream-audio-url",
                        "0",
                        "token",
                        "Песня",
                        "Артист",
                        "https://example.com/art.png",
                        "https://example.com/background-image.png"
                },
                new String[] {
                        (directive.getStartAccountLinking() == null) + "",
                        directive.getAudioPlayer().getAction(),
                        directive.getAudioPlayer().getItem().getStream().getUrl(),
                        directive.getAudioPlayer().getItem().getStream().getOffsetMS() + "",
                        directive.getAudioPlayer().getItem().getStream().getToken(),
                        directive.getAudioPlayer().getItem().getMetadata().getTitle(),
                        directive.getAudioPlayer().getItem().getMetadata().getSubtitle(),
                        directive.getAudioPlayer().getItem().getMetadata().getArt().getUrl(),
                        directive.getAudioPlayer().getItem().getMetadata().getBackgroundImage().getUrl()
                }
        );
    }

    @Test
    public void test2() throws JsonProcessingException {
        Directive directive = getDirective("{\"start_account_linking\": {}}");
        Assertions.assertArrayEquals(
                new String[] {
                        "{}",
                        "true"
                },
                new String[] {
                        directive.getStartAccountLinking().toString(),
                        (directive.getAudioPlayer() == null) + ""
                }
        );
    }
}
