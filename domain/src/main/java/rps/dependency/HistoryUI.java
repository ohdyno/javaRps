package rps.dependency;

import rps.entity.Round;

import java.util.Collection;

public interface HistoryUI {
    void noRounds();

    void roundsPlayed(Collection<Round> rounds);
}
