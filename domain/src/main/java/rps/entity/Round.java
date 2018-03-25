package rps.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
@AllArgsConstructor
public class Round {
    private final Throws p1Throw;
    private final Throws p2Throw;
    private final Result result;

    public <T> T printPlayer1Throw(ThrowsPrinter<T> printer) {
        return p1Throw.print(printer);
    }

    public <T> T printPlayer2Throw(ThrowsPrinter<T> printer) {
        return p2Throw.print(printer);
    }

    public Result getResult() {
        return result;
    }

    public Throws getP1Throw() {
        return p1Throw;
    }

    public Throws getP2Throw() {
        return p2Throw;
    }

    public <R> R printResult(ResultPrinter<R> printer) {
        return result.print(printer);
    }
}
