package io.pivotal.xzhou.rps.webui.boundaries;

import io.pivotal.xzhou.rps.webui.dto.JsonContainer;
import io.pivotal.xzhou.rps.webui.dto.PlayResultAsJson;
import rps.dependency.PlayUI;

public class JsonContainerToPlayUIContainer implements PlayUI {
    private JsonContainer container;

    @Override
    public void player1Wins() {
        container = new PlayResultAsJson("player 1 wins");
    }

    @Override
    public void player2Wins() {
        container = new PlayResultAsJson("player 2 wins");
    }

    @Override
    public void tie() {
        container = new PlayResultAsJson("tie");
    }

    public JsonContainer getContainer() {
        return container;
    }
}
