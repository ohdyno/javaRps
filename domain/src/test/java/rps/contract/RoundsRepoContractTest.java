package rps.contract;

import org.junit.Test;
import rps.dependency.RoundsRepo;
import rps.entity.Round;

import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;
import static rps.entity.Results.Tie;
import static rps.entity.Throws.*;

public abstract class RoundsRepoContractTest {

    public abstract RoundsRepo createRepo();

    @Test
    public void givenNoRoundsSaved_whenGettingTheHistory_thenTheResultIsEmpty() {
        RoundsRepo repo = createRepo();
        assertThat(repo.isEmpty()).isTrue();
    }

    @Test
    public void givenRoundsWereSaved_whenGettingTheHistory_thenTheResultContainsAllOfTheRounds() {
        RoundsRepo repo = createRepo();
        Round round1 = new Round(Rock, Paper, Tie);
        Round round2 = new Round(Rock, Scissors, Tie);
        repo.save(round1);
        repo.save(round2);

        Collection<Round> savedRounds = repo.findAll();

        assertThat(savedRounds).containsExactlyInAnyOrder(round1, round2);
    }
}