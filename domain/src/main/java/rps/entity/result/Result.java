package rps.entity.result;

import rps.entity.ResultPrinter;

public interface Result {
    <R> R print(ResultPrinter<R> printer);

    static Result Player1Wins() {
        return new Player1Wins();
    }

    static Result Tie() {
        return new Tie();
    }

    static Result Player2Wins() {
        return new Player2Wins();
    }
}