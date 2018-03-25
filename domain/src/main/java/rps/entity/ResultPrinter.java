package rps.entity;

public interface ResultPrinter<R> {
    R tie();

    R player1Wins();

    R player2Wins();
}
