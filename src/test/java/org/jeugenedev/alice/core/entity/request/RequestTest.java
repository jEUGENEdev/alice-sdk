package org.jeugenedev.alice.core.entity.request;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RequestTest {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void serializationFromJSONToPOJOShouldNotThrowExceptionForSimpleUtteranceType() throws JsonProcessingException {
        String jsonTest = "{\"request\":{\"command\":\"закажипиццунаулицульватолстого16назавтра\",\"original_utterance\":\"закажипиццунаулицульватолстого,16назавтра\",\"markup\":{\"dangerous_context\":true},\"payload\":{},\"nlu\":{\"tokens\":[\"закажи\",\"пиццу\",\"на\",\"льва\",\"толстого\",\"16\",\"на\",\"завтра\"],\"entities\":[{\"tokens\":{\"start\":2,\"end\":6},\"type\":\"YANDEX.GEO\",\"value\":{\"house_number\":\"16\",\"street\":\"льватолстого\"}},{\"tokens\":{\"start\":3,\"end\":5},\"type\":\"YANDEX.FIO\",\"value\":{\"first_name\":\"лев\",\"last_name\":\"толстой\"}},{\"tokens\":{\"start\":5,\"end\":6},\"type\":\"YANDEX.NUMBER\",\"value\":16},{\"tokens\":{\"start\":6,\"end\":8},\"type\":\"YANDEX.DATETIME\",\"value\":{\"day\":1,\"day_is_relative\":true}}],\"intents\":{}},\"type\":\"SimpleUtterance\"}}";
        JsonNode root = objectMapper.readTree(jsonTest);
        Request request = objectMapper.treeToValue(root.get("request"), Request.class);
        System.out.println(request);
        Assertions.assertArrayEquals(
                new String[] {
                        "закажипиццунаулицульватолстого16назавтра",
                        "закажипиццунаулицульватолстого,16назавтра",
                        "true",
                        "{}",
                        "завтра",
                        "{\"day\":1,\"day_is_relative\":true}",
                        "YANDEX.DATETIME",
                        "6",
                        "8",
                        "{}",
                        "SimpleUtterance"
                },
                new String[] {
                        request.getCommand(),
                        request.getOriginalUtterance(),
                        request.getMarkup().isDangerousContext() + "",
                        request.getPayload(),
                        request.getNlu().getTokens().get(request.getNlu().getTokens().size() - 1),
                        request.getNlu().getEntities().get(request.getNlu().getEntities().size() - 1).getValue(),
                        request.getNlu().getEntities().get(request.getNlu().getEntities().size() - 1).getType(),
                        request.getNlu().getEntities().get(request.getNlu().getEntities().size() - 1).getTokens().getStart() + "",
                        request.getNlu().getEntities().get(request.getNlu().getEntities().size() - 1).getTokens().getEnd() + "",
                        request.getNlu().getIntents(),
                        request.getType()
                }
        );
    }
}
