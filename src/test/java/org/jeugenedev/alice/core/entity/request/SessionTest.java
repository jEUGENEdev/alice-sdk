package org.jeugenedev.alice.core.entity.request;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class SessionTest {
    @Test
    public void jsonShouldConvertCorrectlyToMetaPOJO() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonTest = "{\"session\":{\"message_id\":0,\"session_id\":\"2eac4854-fce721f3-b845abba-20d60\",\"skill_id\":\"3ad36498-f5rd-4079-a14b-788652932056\",\"user_id\":\"47C73714B580ED2469056E71081159529FFC676A4E5B059D629A819E857DC2F8\",\"user\":{\"user_id\":\"6C91DA5198D1758C6A9F63A7C5CDDF09359F683B13A18A151FBF4C8B092BB0C2\",\"access_token\":\"AgAAAAAB4vpbAAApoR1oaCd5yR6eiXSHqOGT8dT\"},\"application\":{\"application_id\":\"47C73714B580ED2469056E71081159529FFC676A4E5B059D629A819E857DC2F8\"},\"new\":true}}";
        JsonNode root = objectMapper.readTree(jsonTest);
        Session session = objectMapper.treeToValue(root.get("session"), Session.class);
        System.out.println(session);
        Assertions.assertArrayEquals(
                new String[] {
                        "0",
                        "2eac4854-fce721f3-b845abba-20d60",
                        "47C73714B580ED2469056E71081159529FFC676A4E5B059D629A819E857DC2F8",
                        "6C91DA5198D1758C6A9F63A7C5CDDF09359F683B13A18A151FBF4C8B092BB0C2",
                        "47C73714B580ED2469056E71081159529FFC676A4E5B059D629A819E857DC2F8"
                },
                new String[] {
                        session.getMessageId() + "",
                        session.getSessionId(),
                        session.getUserId(),
                        session.getUser().getUserId(),
                        session.getApplication().getApplicationId()
                });
    }
}
