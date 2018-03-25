package io.pivotal.xzhou.rps.webui.adapters;

import io.pivotal.xzhou.rps.webui.dto.PlayResultAsJson;
import rps.dependency.processor.PlayResultProcessorDelegate;

public class PlayResultToJsonProcessor implements PlayResultProcessorDelegate<String> {

    @Override
    public String player1Wins() {
        return new PlayResultAsJson("player 1 wins").toJson();
    }

    @Override
    public String player2Wins() {
        return new PlayResultAsJson("player 2 wins").toJson();
    }

    @Override
    public String tie() {
        return new PlayResultAsJson("tie").toJson();
    }

}
