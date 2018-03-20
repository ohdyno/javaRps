package rps;

import com.greghaskins.spectrum.Spectrum;
import org.junit.runner.RunWith;
import rps.doubles.history.FakeRoundsRepo;
import rps.doubles.history.HistoryUISpy;
import rps.doubles.play.PlayUIDummy;
import rps.entity.Results;
import rps.entity.Round;

import java.util.Arrays;
import java.util.function.Supplier;

import static com.greghaskins.spectrum.Spectrum.*;
import static rps.assertions.HistoryUISpyAssert.assertThat;
import static rps.entity.Throws.*;

@RunWith(Spectrum.class)
public class HistoryTest {

    public HistoryTest() {
        describe("For the History feature", () -> {
            Supplier<RPS> rps = let(() -> new RPS(new FakeRoundsRepo()));
            Supplier<HistoryUISpy> uiSpy = let(HistoryUISpy::new);
            PlayUIDummy dummy = new PlayUIDummy();

            describe("given no rounds have been played", () -> {
                describe("when the user requests the history", () -> {
                    it("then tells the PlayUI no rounds have been played", () -> {
                        rps.get().getHistory(uiSpy.get());

                        assertThat(uiSpy.get()).noRoundsWasCalled();
                    });
                });
            });

            describe("given rounds have been played", () -> {
                describe("when the user requests the history", () -> {
                    it("then tells the PlayUI the rounds that have been played", () -> {
                        rps.get().playRound(Rock, Scissors, dummy);
                        rps.get().playRound(Paper, Paper, dummy);

                        rps.get().getHistory(uiSpy.get());

                        assertThat(uiSpy.get()).roundsPlayedWasCalledWith(
                                Arrays.asList(
                                        new Round(Rock, Scissors, Results.Player1Wins),
                                        new Round(Paper, Paper, Results.Tie)
                                )
                        );
                    });
                });
            });
        });
    }


}
