package io.pivotal.xzhou.rps.webui.facade;

import org.json.JSONException;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import rps.RockPaperScissors;
import rps.entity.Throws;
import rps.dependency.HistoryUI;
import rps.dependency.PlayUI;

public class GamePlayTest {

    @Test
    public void givenPlayer1WillWin_whenPlayingARound_theGameShouldReturnThatPlayer1HasWon() throws JSONException {
        RockPaperScissors rps = player1WinsStub();
        Game game = new Game(rps);
        JSONAssert.assertEquals("{result:\"player 1 wins\"}", game.play("rock", "paper").toJson(), false);
    }

    private RockPaperScissors player1WinsStub() {
        return new RockPaperScissors() {
            @Override
            public void playRound(Throws p1Throw, Throws p2Throw, PlayUI ui) {
                ui.player1Wins();
            }

            @Override
            public void getHistory(HistoryUI ui) {

            }
        };
    }

    @Test
    public void givenPlayer2WillWin_whenPlayingARound_theGameShouldReturnThatPlayer2HasWon() throws JSONException {
        RockPaperScissors rps = player2WinsStub();
        Game game = new Game(rps);
        JSONAssert.assertEquals("{result:\"player 2 wins\"}", game.play("rock", "rock").toJson(), false);
    }

    private RockPaperScissors player2WinsStub() {
        return new RockPaperScissors() {
            @Override
            public void playRound(Throws p1Throw, Throws p2Throw, PlayUI ui) {
                ui.player2Wins();
            }

            @Override
            public void getHistory(HistoryUI ui) {

            }
        };
    }

    @Test
    public void givenTheRoundWillBeATie_whenPlayingARound_theGameShouldReturnThatItWasATie() throws JSONException {
        RockPaperScissors rps = tieStub();
        Game game = new Game(rps);
        JSONAssert.assertEquals("{result:\"tie\"}", game.play("rock", "rock").toJson(), false);
    }

    private RockPaperScissors tieStub() {
        return new RockPaperScissors() {
            @Override
            public void playRound(Throws p1Throw, Throws p2Throw, PlayUI ui) {
                ui.tie();
            }

            @Override
            public void getHistory(HistoryUI ui) {

            }
        };
    }

    @Test(expected = Game.IllegalThrowException.class)
    public void givenPlayer1ThrowIsInvalid_whenPlayingARound_theGameShouldThrowIllegalThrowException() {
        Game game = new Game(null);
        game.play("sailboat", "rock");
    }

    @Test(expected = Game.IllegalThrowException.class)
    public void givenPlayer2ThrowIsInvalid_whenPlayingARound_theGameShouldThrowIllegalThrowException() {
        Game game = new Game(null);
        game.play("rock", "sailboat");
    }
}