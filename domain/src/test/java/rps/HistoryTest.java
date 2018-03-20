package rps;

import com.greghaskins.spectrum.Spectrum;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.MockitoAnnotations;
import rps.dependency.HistoryUI;
import rps.dependency.PlayUI;
import rps.doubles.FakeRoundsRepo;
import rps.entity.Results;
import rps.entity.Round;

import java.util.List;

import static com.greghaskins.spectrum.dsl.gherkin.Gherkin.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static rps.entity.Throws.Rock;
import static rps.entity.Throws.Scissors;

@RunWith(Spectrum.class)
public class HistoryTest {

    @Captor
    private ArgumentCaptor<List<Round>> captor;

    public HistoryTest() {
        feature("History of Rounds Played", () -> {
            MockitoAnnotations.initMocks(this);
            RPS rps = new RPS(new FakeRoundsRepo());

            scenario("No rounds have been played", () -> {
                HistoryUI uiSpy = mock(HistoryUI.class);

                when("the user requests the history", () -> {
                    rps.getHistory(uiSpy);
                });

                then("the PlayUI is told that no rounds have been played", () -> {
                    verify(uiSpy).noRounds();
                });
            });

            scenario("Rounds have been played", () -> {
                HistoryUI uiSpy = mock(HistoryUI.class);

                given("rounds have been played before", () -> {
                    rps.playRound(Rock, Scissors, mock(PlayUI.class));
                });

                when("the user requests the history", () -> {
                    rps.getHistory(uiSpy);
                });

                then("the PlayUI is given the rounds that have been played", () -> {
                    verify(uiSpy).roundsPlayed(captor.capture());
                    List<Round> rounds = captor.getValue();
                    assertThat(rounds).contains(new Round(Rock, Scissors, Results.Player1Wins));
                });
            });
        });
    }
}
