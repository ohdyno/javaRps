package rps.doubles.play;

import rps.dependency.PlayResultProcessorDelegate;

public class ResultProcessorProcessorDelegateStub implements PlayResultProcessorDelegate<String> {
    private final String messageForPlayer1Wins;
    private final String messageForPlayer2Wins;
    private final String messageForTie;

    public ResultProcessorProcessorDelegateStub(String messageForPlayer1Wins, String messageForPlayer2Wins, String messageForTie) {
        this.messageForPlayer1Wins = messageForPlayer1Wins;
        this.messageForPlayer2Wins = messageForPlayer2Wins;
        this.messageForTie = messageForTie;
    }

    @Override
    public String player1Wins() {
        return messageForPlayer1Wins;
    }

    @Override
    public String player2Wins() {
        return messageForPlayer2Wins;
    }

    @Override
    public String tie() {
        return messageForTie;
    }
}
