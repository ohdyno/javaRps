package io.pivotal.xzhou.rps.webui.facade;

import io.pivotal.xzhou.rps.webui.boundaries.JsonContainerToHistoryProcessorDelegateAdapter;
import io.pivotal.xzhou.rps.webui.boundaries.PlayResultToJsonProcessor;
import rps.RPS;
import rps.entity.Throws;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static rps.entity.Throws.*;

public class Game {
    private final RPS rps;

    public Game(RPS rps) {
        this.rps = rps;
    }

    public String play(String p1Throw, String p2Throw) {
        validateThrows(Arrays.asList(p1Throw, p2Throw));
        return rps.playRound(convertToRPSThrows(p1Throw), convertToRPSThrows(p2Throw), new PlayResultToJsonProcessor());
    }

    private void validateThrows(List<String> rpsThrows) {
        List<String> invalidThrows = new ArrayList<>();
        rpsThrows.forEach(rpsThrow -> {
            if (invalidThrow(rpsThrow)) {
                invalidThrows.add(rpsThrow);
            }
        });

        if (!invalidThrows.isEmpty()) {
            throw new InvalidThrow(invalidThrows);
        }
    }

    private boolean invalidThrow(String rpsThrow) {
        return !(rpsThrow.equalsIgnoreCase("rock") || rpsThrow.equalsIgnoreCase("scissors") || rpsThrow.equalsIgnoreCase("paper"));
    }

    private Throws convertToRPSThrows(String rpsThrow) {
        if (rpsThrow.equalsIgnoreCase("rock")) {
            return Rock;
        } else if (rpsThrow.equalsIgnoreCase("scissors")) {
            return Scissors;
        } else {
            return Paper;
        }
    }

    public String getHistory() {
        JsonContainerToHistoryProcessorDelegateAdapter resultHandler = new JsonContainerToHistoryProcessorDelegateAdapter();
        rps.getHistory(resultHandler);
        return resultHandler.getContainer().toJson();
    }

    public static class InvalidThrow extends RuntimeException {
        private final List<String> invalidThrows;

        InvalidThrow(List<String> invalidThrows) {
            this.invalidThrows = invalidThrows;
        }

        public List<String> getInvalidThrows() {
            return invalidThrows;
        }
    }
}
