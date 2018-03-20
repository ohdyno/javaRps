package rps;

import com.greghaskins.spectrum.Spectrum;
import org.junit.runner.RunWith;
import rps.dependency.PlayUI;
import rps.doubles.RoundsRepoDummy;

import static com.greghaskins.spectrum.dsl.gherkin.Gherkin.*;
import static com.greghaskins.spectrum.dsl.gherkin.Gherkin.when;
import static org.mockito.Mockito.*;
import static rps.entity.Throws.*;

@RunWith(Spectrum.class)
public class PlayTest {
    public PlayTest() {
        feature("Playing a round of RPS", () -> {
            RPS rps = new RPS(new RoundsRepoDummy());

            scenarioOutline("player 1 wins", (player1Throw, player2Throw) -> {
                        PlayUI uiSpy = mock(PlayUI.class);

                        when("player 1 throws " + player1Throw + " and player 2 throws " + player2Throw, () -> {
                            rps.playRound(player1Throw, player2Throw, uiSpy);
                        });

                        then("PlayUI is told that player 1 wins", () -> {
                            verify(uiSpy).player1Wins();
                        });

                        and("PlayUI is not told that player 2 wins", () -> {
                            verify(uiSpy, never()).player2Wins();
                        });
                    },

                    withExamples(
                            example(Rock, Scissors),
                            example(Scissors, Paper),
                            example(Paper, Rock)
                    )
            );

            scenarioOutline("player 2 wins", (player1Throw, player2Throw) -> {
                        PlayUI uiSpy = mock(PlayUI.class);

                        when("player 1 throws " + player1Throw + " and player 2 throws " + player2Throw, () -> {
                            rps.playRound(player1Throw, player2Throw, uiSpy);
                        });

                        then("PlayUI is told that player 2 wins", () -> {
                            verify(uiSpy).player2Wins();
                        });

                        and("PlayUI is not told that player 1 wins", () -> {
                            verify(uiSpy, never()).player1Wins();
                        });
                    },

                    withExamples(
                            example(Scissors, Rock),
                            example(Paper, Scissors),
                            example(Rock, Paper)
                    )
            );

            scenarioOutline("tie", (sameThrow) -> {
                        PlayUI uiSpy = mock(PlayUI.class);

                        when("player 1 throws " + sameThrow + " and player 2 throws " + sameThrow, () -> {
                            rps.playRound(sameThrow, sameThrow, uiSpy);
                        });

                        then("PlayUI is told that it is a tie", () -> {
                            verify(uiSpy).tie();
                        });

                        and("PlayUI is not told that player 2 wins", () -> {
                            verify(uiSpy, never()).player2Wins();
                        });

                        and("PlayUI is not told that player 1 wins", () -> {
                            verify(uiSpy, never()).player1Wins();
                        });
                    },

                    withExamples(
                            example(Scissors),
                            example(Paper),
                            example(Rock)
                    )
            );
        });
    }
}
