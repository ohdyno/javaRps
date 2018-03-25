package rps.entity.result;

import lombok.EqualsAndHashCode;
import rps.dependency.printer.ResultPrinter;

@EqualsAndHashCode
public class Player2Wins implements Result {
    private final int id = "player 2 wins".hashCode();

    @Override
    public <R> R print(ResultPrinter<R> printer) {
        return printer.player2Wins();
    }
}
