package rps.doubles.play;

import rps.dependency.PlayUI;

public class PlayUIDummy implements PlayUI<Void> {
    @Override
    public Void player1Wins() {
        return null;
    }

    @Override
    public Void player2Wins() {
        return null;
    }

    @Override
    public Void tie() {
        return null;
    }
}
