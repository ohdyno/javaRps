package io.pivotal.xzhou.rps.webui.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import rps.dependency.printer.ResultPrinter;
import rps.entity.Round;
import rps.dependency.printer.ShapesPrinter;

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
            return round.printPlayer1Throw(new JsonPrinter());
        }

        @JsonProperty("p2")
        public String jsonifyP2Throw() {
            return round.printPlayer2Throw(new JsonPrinter());
        }

        @JsonProperty("result")
        public String jsonifyResult() {
            return round.printResult(new JsonPrinter());
        }

        private class JsonPrinter implements ShapesPrinter<String>,ResultPrinter<String> {
            @Override
            public String rock() {
                return "rock";
            }

            @Override
            public String paper() {
                return "paper";
            }

            @Override
            public String scissors() {
                return "scissors";
            }

            @Override
            public String tie() {
                return "tie";
            }

            @Override
            public String player1Wins() {
                return "player 1 wins";
            }

            @Override
            public String player2Wins() {
                return "player 2 wins";
            }
        }
    }
}
