package rps.doubles.play;

import rps.dependency.PlayResultProcessorDelegate;

public class PlayResultProcessorDelegateDummy implements PlayResultProcessorDelegate<Void> {
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
