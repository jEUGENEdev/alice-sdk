package org.jeugenedev.alice.core.entity.request;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RequestTest {
    private final ObjectMapper objectMapper = new ObjectMapper();

    private Request getRequest(String testJson) throws JsonProcessingException {
        JsonNode root = objectMapper.readTree(testJson);
        return objectMapper.treeToValue(root.get("request"), Request.class);
    }

    @Test
    public void serializationFromJSONToPOJOShouldNotThrowExceptionForSimpleUtteranceType() throws JsonProcessingException {
        Request request = getRequest("{\"request\":{\"command\":\"закажипиццунаулицульватолстого16назавтра\",\"original_utterance\":\"закажипиццунаулицульватолстого,16назавтра\",\"markup\":{\"dangerous_context\":true},\"payload\":{},\"nlu\":{\"tokens\":[\"закажи\",\"пиццу\",\"на\",\"льва\",\"толстого\",\"16\",\"на\",\"завтра\"],\"entities\":[{\"tokens\":{\"start\":2,\"end\":6},\"type\":\"YANDEX.GEO\",\"value\":{\"house_number\":\"16\",\"street\":\"льватолстого\"}},{\"tokens\":{\"start\":3,\"end\":5},\"type\":\"YANDEX.FIO\",\"value\":{\"first_name\":\"лев\",\"last_name\":\"толстой\"}},{\"tokens\":{\"start\":5,\"end\":6},\"type\":\"YANDEX.NUMBER\",\"value\":16},{\"tokens\":{\"start\":6,\"end\":8},\"type\":\"YANDEX.DATETIME\",\"value\":{\"day\":1,\"day_is_relative\":true}}],\"intents\":{}},\"type\":\"SimpleUtterance\"}}");
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

    @Test
    public void forTypeButtonPressed() throws JsonProcessingException {
        Request request = getRequest("{\"request\": {\"nlu\": {\"tokens\": [\"надпись\",\"на\",\"кнопке\"],\"entities\": [],\"intents\": {}},\"payload\": {},\"type\": \"ButtonPressed\"}}");
        System.out.println(request);
        Assertions.assertArrayEquals(
                new String[] {
                        null,
                        null,
                        "ButtonPressed",
                        "надпись на кнопке",
                        "true",
                        "{}"
                },
                new String[] {
                        request.getCommand(),
                        request.getOriginalUtterance(),
                        request.getType(),
                        String.join(" ", request.getNlu().getTokens()),
                        request.getNlu().getEntities().isEmpty() + "",
                        request.getNlu().getIntents()
                }
        );
    }

    @Test
    public void forTypePlaybackFailed() throws JsonProcessingException {
        Request request = getRequest("{\"request\": {\"type\": \"AudioPlayer.PlaybackFailed\",\"error\": {\"message\" : \"fail details\",\"type\": \"MEDIA_ERROR_UNKNOWN\"}}}");
        System.out.println(request);
        Assertions.assertArrayEquals(
                new String[] {
                        "AudioPlayer.PlaybackFailed",
                        "fail details",
                        "MEDIA_ERROR_UNKNOWN"
                },
                new String[] {
                        request.getType(),
                        request.getError().getMessage(),
                        request.getError().getType()
                }
        );
    }

    @Test
    public void forTypePlaybackNearlyFinished() throws JsonProcessingException {
        Request request = getRequest("{\"request\": {\"type\": \"AudioPlayer.PlaybackNearlyFinished\"}}");
        System.out.println(request);
        Assertions.assertEquals("AudioPlayer.PlaybackNearlyFinished", request.getType());
    }

    @Test
    public void forTypePlaybackStopped() throws JsonProcessingException {
        Request request = getRequest("{\"request\": {\"type\": \"AudioPlayer.PlaybackStopped\"}}");
        System.out.println(request);
        Assertions.assertEquals("AudioPlayer.PlaybackStopped", request.getType());
    }

    @Test
    public void forTypePurchaseConfirmation() throws JsonProcessingException {
        Request request = getRequest("{\"request\": {\"type\": \"Purchase.Confirmation\",\"purchase_request_id\": \"d432de19be8347d09f656d9fe966e2f9\",\"purchase_token\": \"token_value\",\"order_id\": \"eeb59d64-9e6a-11ea-bb37-0242ac130002\",\"purchase_timestamp\": 1590399311,\"purchase_payload\": {\"value\": \"payload\"},\"signed_data\": \"purchase_request_id=id_value&purchase_token=token_value&order_id=id_value&...\",\"signature\": \"Pi6JNCFeeleRa...\"}}");
        System.out.println(request);
        Assertions.assertArrayEquals(
                new String[] {
                        "Purchase.Confirmation",
                        "d432de19be8347d09f656d9fe966e2f9",
                        "token_value",
                        "eeb59d64-9e6a-11ea-bb37-0242ac130002",
                        "1590399311",
                        "\"payload\"",
                        "purchase_request_id=id_value&purchase_token=token_value&order_id=id_value&...",
                        "Pi6JNCFeeleRa..."
                },
                new String[] {
                        request.getType(),
                        request.getPurchaseRequestId(),
                        request.getPurchaseToken(),
                        request.getOrderId(),
                        request.getPurchaseTimestamp() + "",
                        request.getPurchasePayload().getValue(),
                        request.getSignedData(),
                        request.getSignature()
                }
        );
    }
}
