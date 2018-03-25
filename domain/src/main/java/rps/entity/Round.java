package rps.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import rps.entity.result.Result;
import rps.entity.shapes.Shapes;
import rps.entity.shapes.ShapesPrinter;

@EqualsAndHashCode
@AllArgsConstructor
public class Round {
    private final Shapes p1Throw;
    private final Shapes p2Throw;
    private final Result result;

    public <T> T printPlayer1Throw(ShapesPrinter<T> printer) {
        return p1Throw.print(printer);
    }

    public <T> T printPlayer2Throw(ShapesPrinter<T> printer) {
        return p2Throw.print(printer);
    }

    public Result getResult() {
        return result;
    }

    public Shapes getP1Throw() {
        return p1Throw;
    }

    public Shapes getP2Throw() {
        return p2Throw;
    }

    public <R> R printResult(ResultPrinter<R> printer) {
        return result.print(printer);
    }
}
