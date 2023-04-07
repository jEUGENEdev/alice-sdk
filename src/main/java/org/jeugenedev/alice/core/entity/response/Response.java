package org.jeugenedev.alice.core.entity.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Response {
    private String text, tts;
    private List<Button> buttons;
    @JsonProperty("end_session") private boolean endSession;
}
