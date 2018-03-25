package rps;

import com.greghaskins.spectrum.Spectrum;
import com.greghaskins.spectrum.Variable;
import org.junit.runner.RunWith;
import rps.dependency.processor.PlayResultProcessorDelegate;
import rps.doubles.play.ResultProcessorProcessorDelegateStub;
import rps.doubles.repo.RoundsRepositoryDummy;
import rps.exceptions.InvalidShape;

import java.util.Collection;

import static com.greghaskins.spectrum.dsl.gherkin.Gherkin.*;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(Spectrum.class)
public class PlayTest {
    public PlayTest() {
        feature("Playing a round of RPS", () -> {
            RPS rps = new RPS(new RoundsRepositoryDummy());
            String messageForPlayer1Wins = "player 1 wins";
            String messageForPlayer2Wins = "player 2 wins";
            String messageForTie = "tie";
            PlayResultProcessorDelegate<String> stub = new ResultProcessorProcessorDelegateStub(messageForPlayer1Wins, messageForPlayer2Wins, messageForTie);
            Variable<String> result = new Variable<>();

            scenarioOutline("player 1 wins", (player1Throw, player2Throw) -> {
                        when("player 1 throws " + player1Throw + " and player 2 throws " + player2Throw, () -> {
                            result.set(rps.playRound(player1Throw, player2Throw, stub));
                        });

                        then("tell the result process delegate that player 1 wins", () -> {
                            assertThat(result.get()).isEqualTo(messageForPlayer1Wins);
                        });
                    },

                    withExamples(
                            example("Rock", "Scissors"),
                            example("Scissors", "Paper"),
                            example("Paper", "Rock")
                    )
            );

            scenarioOutline("player 2 wins", (player1Throw, player2Throw) -> {
                        when("player 1 throws " + player1Throw + " and player 2 throws " + player2Throw, () -> {
                            result.set(rps.playRound(player1Throw, player2Throw, stub));
                        });

                        then("tell the result process delegate that player 2 wins", () -> {
                            assertThat(result.get()).isEqualTo(messageForPlayer2Wins);
                        });
                    },

                    withExamples(
                            example("Scissors", "Rock"),
                            example("Paper", "Scissors"),
                            example("Rock", "Paper")
                    )
            );

            scenarioOutline("tie", (sameThrow) -> {
                        when("player 1 throws " + sameThrow + " and player 2 throws " + sameThrow, () -> {
                            result.set(rps.playRound(sameThrow, sameThrow, stub));
                        });

                        then("tell the result process delegate that player 2 wins", () -> {
                            assertThat(result.get()).isEqualTo(messageForTie);
                        });
                    },

                    withExamples(
                            example("Scissors"),
                            example("Paper"),
                            example("Rock")
                    )
            );

            scenario("invalid throws", () -> {
                Variable<String> player1Throw = new Variable<>();
                Variable<String> player2Throw = new Variable<>();
                Variable<InvalidShape> invalidThrows = new Variable<>();

                given("invalid throws for both throws", () -> {
                    player1Throw.set("sailboat");
                    player2Throw.set("something invalid");
                });

                when("play a round", () -> {
                    try {
                        rps.playRound(player1Throw.get(), player2Throw.get(), stub);
                    } catch (InvalidShape e) {
                        invalidThrows.set(e);
                    }
                });

                then("receive an exception that contains a list of invalid throws", () -> {
                    Collection<String> invalidThrowsFromException = invalidThrows.get().getInvalids();
                    assertThat(invalidThrowsFromException).containsExactlyInAnyOrder(player1Throw.get(), player2Throw.get());
                });
            });
        });
    }

}
