package io.pivotal.xzhou.rps.webui.adapters;

import io.pivotal.xzhou.rps.webui.dto.HistoryAsJson;
import rps.dependency.HistoryProcessorDelegate;
import rps.entity.Round;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class HistoryToJsonProcessor implements HistoryProcessorDelegate<String> {

    @Override
    public String noRounds() {
        return new HistoryAsJson(Collections.emptyList()).toJson();
    }

    @Override
    public String roundsPlayed(Collection<Round> rounds) {
        return new HistoryAsJson(new ArrayList<>(rounds)).toJson();
    }

}
