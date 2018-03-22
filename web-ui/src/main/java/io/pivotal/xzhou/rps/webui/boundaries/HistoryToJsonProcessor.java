package io.pivotal.xzhou.rps.webui.boundaries;

import io.pivotal.xzhou.rps.webui.dto.HistoryAsJson;
import io.pivotal.xzhou.rps.webui.dto.JsonContainer;
import rps.dependency.HistoryProcessorDelegate;
import rps.entity.Round;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class HistoryToJsonProcessor implements HistoryProcessorDelegate<String> {
    private JsonContainer container;

    @Override
    public String noRounds() {
        return new HistoryAsJson(Collections.emptyList()).toJson();
    }

    @Override
    public String roundsPlayed(Collection<Round> rounds) {
        return new HistoryAsJson(new ArrayList<>(rounds)).toJson();
    }

    public JsonContainer getContainer() {
        return container;
    }
}
