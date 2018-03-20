package io.pivotal.xzhou.rps.webui.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class InvalidThrowsAsJson implements JsonContainer {
    @JsonProperty("invalid_throws")
    private List<String> invalidThrows;

    public InvalidThrowsAsJson(List<String> invalidThrows) {
        this.invalidThrows = invalidThrows;
    }

    @Override
    public String toJson() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
