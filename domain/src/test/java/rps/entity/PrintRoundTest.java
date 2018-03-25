package rps.entity;

import com.greghaskins.spectrum.Spectrum;
import com.greghaskins.spectrum.Variable;
import org.junit.runner.RunWith;
import rps.entity.result.Result;

import static com.greghaskins.spectrum.dsl.gherkin.Gherkin.*;
import static org.assertj.core.api.Assertions.assertThat;
import static rps.entity.Throws.*;

@RunWith(Spectrum.class)
public class PrintRoundTest {

    PrintRoundTest() {
        feature("Printing a throw", () -> {
            Variable<String> printed = new Variable<>();

            Variable<Round> round = new Variable<>();
            final String rockPrint = "print a rock";
            final String paperPrint = "print a paper";
            final String scissorsPrint = "print scissors";
            ThrowsPrinter<String> stub = new ThrowsPrinter<String>() {
                @Override
                public String rock() {
                    return rockPrint;
                }

                @Override
                public String paper() {
                    return paperPrint;
                }

                @Override
                public String scissors() {
                    return scissorsPrint;
                }
            };

            scenarioOutline("player 1 throw is a valid throw", (p1Throw, p1ThrowPrint) -> {
                        given("player 1 throw is '" + p1Throw.toString() + "'", () -> {
                            round.set(new Round(p1Throw, null, null));
                        });

                        when("printing player 1 throw with a printer", () -> {
                            printed.set(round.get().printPlayer1Throw(stub));
                        });

                        then("the result should be '" + p1ThrowPrint + "'", () -> {
                            assertThat(printed.get()).isEqualTo(p1ThrowPrint);
                        });
                    },
                    withExamples(
                            example(Rock(), rockPrint),
                            example(Paper(), paperPrint),
                            example(Scissors(), scissorsPrint)
                    )
            );

            scenarioOutline("player 2 throw is a valid throw", (p2Throw, p2ThrowPrint) -> {
                        given("player 2 throw is '" + p2Throw.toString() + "'", () -> {
                            round.set(new Round(null, p2Throw, null));
                        });

                        when("printing player 2 throw with a printer", () -> {
                            printed.set(round.get().printPlayer2Throw(stub));
                        });

                        then("the result should be '" + p2ThrowPrint + "'", () -> {
                            assertThat(printed.get()).isEqualTo(p2ThrowPrint);
                        });
                    },
                    withExamples(
                            example(Rock(), rockPrint),
                            example(Paper(), paperPrint),
                            example(Scissors(), scissorsPrint)
                    )
            );
        });

        feature("Printing a result", () -> {
            Variable<String> printed = new Variable<>();

            Variable<Round> round = new Variable<>();
            final String tiePrint = "tie";
            final String player1WinsPrint = "player 1 wins";
            final String player2WinsPrint = "player 2 wins";
            ResultPrinter<String> stub = new ResultPrinter<String>() {
                @Override
                public String tie() {
                    return tiePrint;
                }

                @Override
                public String player1Wins() {
                    return player1WinsPrint;
                }

                @Override
                public String player2Wins() {
                    return player2WinsPrint;
                }
            };

            scenarioOutline("the result is a tie", (result, resultPrint) -> {
                        given("result is '" + result.toString() + "'", () -> {
                            round.set(new Round(null, null, result));
                        });

                        when("printing the result with a printer", () -> {
                            printed.set(round.get().printResult(stub));
                        });

                        then("the result should be '" + resultPrint + "'", () -> {
                            assertThat(printed.get()).isEqualTo(resultPrint);
                        });
                    },
                    withExamples(
                            example(Result.Tie(), tiePrint),
                            example(Result.Player1Wins(), player1WinsPrint),
                            example(Result.Player2Wins(), player2WinsPrint)
                    )
            );
        });
    }
}