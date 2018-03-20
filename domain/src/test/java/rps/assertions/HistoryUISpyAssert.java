package rps.assertions;

import org.assertj.core.api.AbstractAssert;
import rps.doubles.HistoryUISpy;
import rps.entity.Round;

import java.util.List;

public class HistoryUISpyAssert extends AbstractAssert<HistoryUISpyAssert, HistoryUISpy> {
        public HistoryUISpyAssert(HistoryUISpy historyUISpy) {
            super(historyUISpy, HistoryUISpyAssert.class);
        }

        public static HistoryUISpyAssert assertThat(HistoryUISpy actual) {
            return new HistoryUISpyAssert(actual);
        }

    public HistoryUISpyAssert noRoundsWasCalled() {
        isNotNull();

        if (!actual.noRoundsWasCalled) {
            failWithMessage("Expected 'noRounds()' to have been called");
        }

        return this;
    }

    public HistoryUISpyAssert roundsPlayedWasCalledWith(List<Round> rounds) {
        isNotNull();

        if(!(actual.rounds.containsAll(rounds) && actual.rounds.size() == rounds.size())) {
            failWithMessage("Expected 'roundsPlayed' to have been called with <%s> but was <%s>",
                    rounds.toString(), actual.rounds.toString());
        }

        return this;
    }
}