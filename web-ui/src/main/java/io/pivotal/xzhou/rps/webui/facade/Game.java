package io.pivotal.xzhou.rps.webui.facade;

import io.pivotal.xzhou.rps.webui.adapters.HistoryToJsonProcessor;
import io.pivotal.xzhou.rps.webui.adapters.PlayResultToJsonProcessor;
import rps.RPS;
import rps.exceptions.InvalidThrows;

public class Game {
    private final RPS rps;

    public Game(RPS rps) {
        this.rps = rps;
    }

    public String play(String p1Throw, String p2Throw) throws InvalidThrows {
        return rps.playRound(p1Throw, p2Throw, new PlayResultToJsonProcessor());
    }

    public String getHistory() {
        return rps.getHistory(new HistoryToJsonProcessor());
    }
}
