package rps.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import rps.entity.result.Result;

@EqualsAndHashCode
@AllArgsConstructor
public class Round {
    private final ThrowsTemporary p1Throw;
    private final ThrowsTemporary p2Throw;
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

    public ThrowsTemporary getP1Throw() {
        return p1Throw;
    }

    public ThrowsTemporary getP2Throw() {
        return p2Throw;
    }

    public <R> R printResult(ResultPrinter<R> printer) {
        return result.print(printer);
    }
}
