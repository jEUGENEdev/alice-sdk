package org.jeugenedev.alice.core.entity.request;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StateTest {
    @Test
    public void jsonShouldConvertCorrectlyToMetaPOJO() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonTest = "{\"state\": {\"session\": {\"value\": 10},\"user\": {\"value\": 42},\"application\": {\"value\": 37}}}";
        JsonNode root = objectMapper.readTree(jsonTest);
        State state = objectMapper.treeToValue(root.get("state"), State.class);
        System.out.println(state);
        Assertions.assertArrayEquals(
                new int[] {
                        10,
                        42,
                        37
                },
                new int[] {
                        Integer.parseInt(state.getSession().getValue().toString()),
                        Integer.parseInt(state.getUser().getValue().toString()),
                        Integer.parseInt(state.getApplication().getValue().toString())
                });
    }
}
