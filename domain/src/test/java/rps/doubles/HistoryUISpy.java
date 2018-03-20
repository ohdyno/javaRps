package rps.doubles;

import rps.dependency.HistoryUI;
import rps.entity.Round;

import java.util.Collection;

public class HistoryUISpy implements HistoryUI {
    public boolean noRoundsWasCalled;
    public Collection<Round> rounds;

    @Override
    public void noRounds() {
        noRoundsWasCalled = true;
    }

    @Override
    public void roundsPlayed(Collection<Round> rounds) {
        this.rounds = rounds;
    }
}
