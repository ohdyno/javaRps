package rps.entity.result;

import lombok.EqualsAndHashCode;
import rps.entity.ResultPrinter;

@EqualsAndHashCode
public class Player1Wins implements Result {
    private final int id = "player 1 wins".hashCode();

    @Override
    public <R> R print(ResultPrinter<R> printer) {
        return printer.player1Wins();
    }
}
