package io.pivotal.xzhou.rps.webui.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import rps.entity.Round;
import rps.entity.Throws;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class HistoryAsJson implements JsonContainer {
    @JsonProperty("rounds")
    private final List<JsonRound> rounds;

    public HistoryAsJson(Collection<Round> rounds) {
        this.rounds = new ArrayList<>();
        rounds.forEach(round -> this.rounds.add(new JsonRound(round)));
    }

    public String toJson() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private class JsonRound {
        private final Round round;

        JsonRound(Round round) {
            this.round = round;
        }

        @JsonProperty("p1")
        public String jsonifyP1Throw() {
            return jsonifyThrow(round.getP1Throw());
        }

        @JsonProperty("p2")
        public String jsonifyP2Throw() {
            return jsonifyThrow(round.getP2Throw());
        }

        @JsonProperty("result")
        public String jsonifyResult() {
            switch (round.getResult()) {
                case Tie:
                    return "tie";
                case Player1Wins:
                    return "player 1 wins";
                case Player2Wins:
                    return "player 2 wins";
                default:
                    return "";
            }
        }

        private String jsonifyThrow(Throws rpsThrow) {
            switch (rpsThrow) {
                case Rock:
                    return "rock";
                case Scissors:
                    return "scissors";
                case Paper:
                    return "paper";
                default:
                    return "";
            }
        }
    }
}
