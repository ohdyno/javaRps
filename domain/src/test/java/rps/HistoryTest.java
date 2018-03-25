package rps;

import com.greghaskins.spectrum.Spectrum;
import org.junit.runner.RunWith;
import rps.doubles.history.HistoryProcessorDelegateStub;
import rps.doubles.play.PlayResultProcessorDelegateDummy;
import rps.doubles.repo.FakeRoundsRepository;
import rps.entity.result.Result;
import rps.entity.Round;
import rps.entity.Throws;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.Supplier;

import static com.greghaskins.spectrum.Spectrum.*;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(Spectrum.class)
public class HistoryTest {

    public HistoryTest() {
        describe("For the History feature", () -> {
            Supplier<RPS> rps = let(() -> new RPS(new FakeRoundsRepository()));
            Supplier<HistoryProcessorDelegateStub> stub = let(() -> new HistoryProcessorDelegateStub());
            PlayResultProcessorDelegateDummy dummy = new PlayResultProcessorDelegateDummy();

            describe("given no rounds have been played", () -> {
                describe("when the user requests the history", () -> {
                    it("then tells the history processor delegate no rounds have been played", () -> {
                        Collection<Round> history = rps.get().getHistory(stub.get());

                        assertThat(history).isSameAs(stub.get().historyForNoRoundsPlayed);
                    });
                });
            });

            describe("given rounds have been played", () -> {
                describe("when the user requests the history", () -> {
                    it("then tells the history processor delegate the rounds that have been played", () -> {
                        rps.get().playRound("rock", "scissors", dummy);
                        rps.get().playRound("paper", "paper", dummy);

                        Collection<Round> history = rps.get().getHistory(stub.get());

                        assertThat(history).containsAll(
                                Arrays.asList(
                                        new Round(Throws.Rock(), Throws.Scissors, Result.Player1Wins()),
                                        new Round(Throws.Paper(), Throws.Paper(), Result.Tie())
                                )
                        );
                    });
                });
            });
        });
    }
}
