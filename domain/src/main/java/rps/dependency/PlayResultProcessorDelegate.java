package rps.dependency;

public interface PlayResultProcessorDelegate<T> {
    T player1Wins();

    T player2Wins();

    T tie();
}
