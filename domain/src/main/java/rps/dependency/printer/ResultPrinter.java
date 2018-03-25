package rps.dependency.printer;

public interface ResultPrinter<R> {
    R tie();

    R player1Wins();

    R player2Wins();
}
