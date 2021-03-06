package rps.contract;

import org.junit.Test;
import rps.dependency.repository.RoundsRepository;
import rps.entity.Round;
import rps.entity.shapes.Shapes;
import rps.entity.result.Result;

import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

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
        Round round1 = new Round(Shapes.Rock(), Shapes.Paper(), Result.Tie());
        Round round2 = new Round(Shapes.Rock(), Shapes.Scissors(), Result.Tie());
        repo.save(round1);
        repo.save(round2);

        Collection<Round> savedRounds = repo.findAll();

        assertThat(savedRounds).containsExactlyInAnyOrder(round1, round2);
    }
}