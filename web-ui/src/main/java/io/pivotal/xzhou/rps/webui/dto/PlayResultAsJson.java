package io.pivotal.xzhou.rps.webui.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PlayResultAsJson implements JsonContainer {
        @JsonProperty("result")
        private final String result;

        public PlayResultAsJson(String result) {
            this.result = result;
        }

        public String toJson() {
            try {
                return new ObjectMapper().writeValueAsString(this);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
    }