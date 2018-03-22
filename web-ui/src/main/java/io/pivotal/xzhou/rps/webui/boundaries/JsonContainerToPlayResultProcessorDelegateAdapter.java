package io.pivotal.xzhou.rps.webui.boundaries;

import io.pivotal.xzhou.rps.webui.dto.JsonContainer;
import io.pivotal.xzhou.rps.webui.dto.PlayResultAsJson;
import rps.dependency.PlayResultProcessorDelegate;

public class JsonContainerToPlayResultProcessorDelegateAdapter implements PlayResultProcessorDelegate<Void> {
    private JsonContainer container;

    @Override
    public Void player1Wins() {
        container = new PlayResultAsJson("player 1 wins");
        return null;
    }

    @Override
    public Void player2Wins() {
        container = new PlayResultAsJson("player 2 wins");
        return null;
    }

    @Override
    public Void tie() {
        container = new PlayResultAsJson("tie");
        return null;
    }

    public JsonContainer getContainer() {
        return container;
    }
}
