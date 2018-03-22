package rps.dependency;

public interface PlayUI<T> {
    T player1Wins();

    T player2Wins();

    T tie();
}
