package rps.entity;

import com.greghaskins.spectrum.Spectrum;
import com.greghaskins.spectrum.Variable;
import org.junit.runner.RunWith;

import static com.greghaskins.spectrum.dsl.gherkin.Gherkin.*;
import static org.assertj.core.api.Assertions.assertThat;
import static rps.entity.Throws.*;

@RunWith(Spectrum.class)
public class RoundPrinterTest {

    RoundPrinterTest() {
        feature("Printing a round", () -> {
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
                            example(Rock, rockPrint),
                            example(Paper, paperPrint),
                            example(Scissors, scissorsPrint)
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
                            example(Rock, rockPrint),
                            example(Paper, paperPrint),
                            example(Scissors, scissorsPrint)
                    )
            );
        });
    }
}