package rps.dependency;

import rps.entity.Round;

import java.util.Collection;

public interface HistoryUI<T> {
    T noRounds();

    T roundsPlayed(Collection<Round> rounds);
}
