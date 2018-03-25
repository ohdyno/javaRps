package rps.entity;

import static rps.entity.Result.*;

public interface ResultTemporary {
    <R> R print(ResultPrinter<R> printer);

    static Result Player1Wins() {
        return Player1Wins;
    }

    static Result Tie() {
        return Tie;
    }

    static Result Player2Wins() {
        return Player2Wins;
    }
}
