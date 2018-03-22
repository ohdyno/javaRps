package io.pivotal.xzhou.rps.webui.boundaries;

import io.pivotal.xzhou.rps.webui.dto.JsonContainer;
import io.pivotal.xzhou.rps.webui.dto.PlayResultAsJson;
import rps.dependency.PlayResultProcessorDelegate;

public class PlayResultToJsonProcessor implements PlayResultProcessorDelegate<String> {
    private JsonContainer container;

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

    public JsonContainer getContainer() {
        return container;
    }
}