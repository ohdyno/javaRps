package rps.contract;

import org.junit.Test;
import rps.dependency.RoundsRepository;
import rps.entity.Round;

import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;
import static rps.entity.Result.Tie;
import static rps.entity.Throws.*;

public abstract class RoundsRepositoryContractTest {

    public abstract RoundsRepository createRepo();

    @Test
    public void givenNoRoundsSaved_whenGettingTheHistory_thenTheResultIsEmpty() {
        RoundsRepository repo = createRepo();
        assertThat(repo.isEmpty()).isTrue();
    }

    @Test
    public void givenRoundsWereSaved_whenGettingTheHistory_thenTheResultContainsAllOfTheRounds() {
        RoundsRepository repo = createRepo();
        Round round1 = new Round(Rock, Paper, Tie);
        Round round2 = new Round(Rock, Scissors, Tie);
        repo.save(round1);
        repo.save(round2);

        Collection<Round> savedRounds = repo.findAll();

        assertThat(savedRounds).containsExactlyInAnyOrder(round1, round2);
    }
}