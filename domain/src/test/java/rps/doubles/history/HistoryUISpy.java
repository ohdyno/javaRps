package rps.doubles.history;

import rps.dependency.HistoryUI;
import rps.entity.Round;

import java.util.Collection;

public class HistoryUISpy implements HistoryUI<Void> {
    public boolean noRoundsWasCalled;
    public Collection<Round> rounds;

    @Override
    public Void noRounds() {
        noRoundsWasCalled = true;
        return null;
    }

    @Override
    public Void roundsPlayed(Collection<Round> rounds) {
        this.rounds = rounds;
        return null;
    }
}
