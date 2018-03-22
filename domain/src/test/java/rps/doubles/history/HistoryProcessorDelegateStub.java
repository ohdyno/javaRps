package rps.doubles.history;

import rps.dependency.HistoryProcessorDelegate;
import rps.entity.Round;

import java.util.ArrayList;
import java.util.Collection;

public class HistoryProcessorDelegateStub implements HistoryProcessorDelegate<Collection<Round>> {
    public final Collection<Round> historyForNoRoundsPlayed = new ArrayList<>();

    @Override
    public Collection<Round> noRounds() {
        return historyForNoRoundsPlayed;
    }

    @Override
    public Collection<Round> roundsPlayed(Collection<Round> rounds) {
        return rounds;
    }
}