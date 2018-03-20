package io.pivotal.xzhou.rps.webui.facade;

import io.pivotal.xzhou.rps.webui.boundaries.JsonContainerToHistoryUIAdapter;
import io.pivotal.xzhou.rps.webui.boundaries.JsonContainerToPlayUIContainer;
import io.pivotal.xzhou.rps.webui.dto.JsonContainer;
import rps.RockPaperScissors;
import rps.entity.Throws;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static rps.entity.Throws.*;

public class Game {
    private final RockPaperScissors rps;

    public Game(RockPaperScissors rps) {
        this.rps = rps;
    }

    public JsonContainer play(String p1Throw, String p2Throw) {
        JsonContainerToPlayUIContainer result = new JsonContainerToPlayUIContainer();
        validateThrows(Arrays.asList(p1Throw, p2Throw));
        rps.playRound(convertToRPSThrows(p1Throw), convertToRPSThrows(p2Throw), result);
        return result.getContainer();
    }

    private void validateThrows(List<String> rpsThrows) {
        List<String> invalidThrows = new ArrayList<>();
        rpsThrows.forEach(rpsThrow -> {
            if (invalidThrow(rpsThrow)) {
                invalidThrows.add(rpsThrow);
            }
        });

        if (!invalidThrows.isEmpty()) {
            throw new IllegalThrowException(invalidThrows);
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


    public JsonContainer getHistory() {
        JsonContainerToHistoryUIAdapter resultHandler = new JsonContainerToHistoryUIAdapter();
        rps.getHistory(resultHandler);
        return resultHandler.getContainer();
    }

    public static class IllegalThrowException extends RuntimeException {
        private final List<String> invalidThrows;

        IllegalThrowException(List<String> invalidThrows) {
            this.invalidThrows = invalidThrows;
        }

        public List<String> getInvalidThrows() {
            return invalidThrows;
        }
    }
}