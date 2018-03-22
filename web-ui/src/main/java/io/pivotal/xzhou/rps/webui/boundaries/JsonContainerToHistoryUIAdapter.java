package io.pivotal.xzhou.rps.webui.boundaries;

import io.pivotal.xzhou.rps.webui.dto.HistoryAsJson;
import io.pivotal.xzhou.rps.webui.dto.JsonContainer;
import rps.entity.Round;
import rps.dependency.HistoryUI;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class JsonContainerToHistoryUIAdapter implements HistoryUI<Void> {
    private JsonContainer container;

    @Override
    public Void noRounds() {
        container = new HistoryAsJson(Collections.emptyList());
        return null;
    }

    @Override
    public Void roundsPlayed(Collection<Round> rounds) {
        container = new HistoryAsJson(new ArrayList<>(rounds));
        return null;
    }

    public JsonContainer getContainer() {
        return container;
    }
}
