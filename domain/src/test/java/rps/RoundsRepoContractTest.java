package rps;

import com.greghaskins.spectrum.Spectrum;
import com.greghaskins.spectrum.Variable;
import org.junit.runner.RunWith;
import rps.dependency.RoundsRepo;
import rps.doubles.FakeRoundsRepo;
import rps.entity.Round;

import static com.greghaskins.spectrum.dsl.gherkin.Gherkin.*;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static rps.entity.Results.Tie;
import static rps.entity.Throws.*;

@RunWith(Spectrum.class)
public class RoundsRepoContractTest {

    public RoundsRepoContractTest() {
        feature("Rounds Repository", () -> {
            Variable<RoundsRepo> repoContainer = new Variable<>();

            scenario("with no rounds saved", () -> {
                given("a new repository", () -> {
                    repoContainer.set(new FakeRoundsRepo());
                });

                then("is it empty", () -> {
                    assertThat(repoContainer.get().isEmpty()).isTrue();
                });
            });

            scenario("with rounds saved", () -> {
                Round round1 = new Round(Rock, Paper, Tie);
                Round round2 = new Round(Rock, Scissors, Tie);

                given("a new repository", () -> {
                    repoContainer.set(new FakeRoundsRepo());
                });

                when("rounds are saved to the repository", () -> {
                    RoundsRepo repo = repoContainer.get();
                    repo.save(round1);
                    repo.save(round2);
                });

                then("it is not empty", () -> {
                    assertThat(repoContainer.get().isEmpty()).isFalse();
                });

                and("it can find all rounds that have been played", () -> {
                    RoundsRepo repo = repoContainer.get();
                    assertThat(repo.findAll()).containsAll(asList(round1, round2));
                });
            });
        });
    }
}