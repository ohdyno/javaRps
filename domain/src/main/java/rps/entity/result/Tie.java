package rps.entity.result;

import lombok.EqualsAndHashCode;
import rps.dependency.printer.ResultPrinter;

@EqualsAndHashCode
public class Tie implements Result {
    private final int id = "tie".hashCode();

    @Override
    public <R> R print(ResultPrinter<R> printer) {
        return printer.tie();
    }
}
