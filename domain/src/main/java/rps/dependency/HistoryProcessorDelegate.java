package rps.dependency;

import rps.entity.Round;

import java.util.Collection;

public interface HistoryProcessorDelegate<T> {
    T noRounds();

    T roundsPlayed(Collection<Round> rounds);
}
