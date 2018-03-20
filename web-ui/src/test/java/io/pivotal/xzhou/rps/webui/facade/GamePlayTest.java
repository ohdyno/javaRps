package io.pivotal.xzhou.rps.webui.facade;

import org.junit.Test;
import org.springframework.test.util.JsonExpectationsHelper;
import rps.RockPaperScissors;
import rps.dependency.HistoryUI;
import rps.dependency.PlayUI;
import rps.entity.Throws;

public class GamePlayTest {

    private JsonExpectationsHelper jsonAssert = new JsonExpectationsHelper();

    @Test
    public void givenPlayer1WillWin_whenPlayingARound_theGameShouldReturnThatPlayer1HasWon() throws Exception {
        RockPaperScissors rps = new Player1WinsStub();
        Game game = new Game(rps);
        jsonAssert.assertJsonEqual("{result:\"player 1 wins\"}", game.play("rock", "paper"));
    }

    @Test
    public void givenPlayer2WillWin_whenPlayingARound_theGameShouldReturnThatPlayer2HasWon() throws Exception {
        RockPaperScissors rps = new Player2WinsStub();
        Game game = new Game(rps);
        jsonAssert.assertJsonEqual("{result:\"player 2 wins\"}", game.play("rock", "rock"));
    }

    @Test
    public void givenTheRoundWillBeATie_whenPlayingARound_theGameShouldReturnThatItWasATie() throws Exception {
        RockPaperScissors rps = new TieStub();
        Game game = new Game(rps);
        jsonAssert.assertJsonEqual("{result:\"tie\"}", game.play("rock", "rock"));
    }

    @Test(expected = Game.InvalidThrow.class)
    public void givenPlayer1ThrowIsInvalid_whenPlayingARound_theGameShouldThrowIllegalThrowException() {
        Game game = new Game(null);
        game.play("sailboat", "rock");
    }

    @Test(expected = Game.InvalidThrow.class)
    public void givenPlayer2ThrowIsInvalid_whenPlayingARound_theGameShouldThrowIllegalThrowException() {
        Game game = new Game(null);
        game.play("rock", "sailboat");
    }

    private class Player1WinsStub implements RockPaperScissors {
        @Override
        public void playRound(Throws p1Throw, Throws p2Throw, PlayUI ui) {
            ui.player1Wins();
        }

        @Override
        public void getHistory(HistoryUI ui) {

        }
    }

    private class Player2WinsStub implements RockPaperScissors {
        @Override
        public void playRound(Throws p1Throw, Throws p2Throw, PlayUI ui) {
            ui.player2Wins();
        }

        @Override
        public void getHistory(HistoryUI ui) {

        }
    }

    private class TieStub implements RockPaperScissors {
        @Override
        public void playRound(Throws p1Throw, Throws p2Throw, PlayUI ui) {
            ui.tie();
        }

        @Override
        public void getHistory(HistoryUI ui) {

        }
    }
}