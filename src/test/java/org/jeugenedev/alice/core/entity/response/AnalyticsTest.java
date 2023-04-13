package org.jeugenedev.alice.core.entity.response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AnalyticsTest {
    @Test
    public void test() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = "{\"events\":[{\"name\":\"custom event\"},{\"name\":\"another custom event\",\"value\":{\"field\":\"some value\",\"second field\":{\"third field\":\"custom value\"}}}]}";
        Analytics analytics = objectMapper.readValue(json, Analytics.class);
        Assertions.assertArrayEquals(
                new String[] {
                        "custom event",
                        null,
                        "another custom event",
                        "{\"field\":\"some value\",\"second field\":{\"third field\":\"custom value\"}}"
                },
                new String[] {
                        analytics.getEvents().get(0).getName(),
                        analytics.getEvents().get(0).getValue(),
                        analytics.getEvents().get(1).getName(),
                        analytics.getEvents().get(1).getValue()
                }
        );
    }
}
