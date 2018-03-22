package io.pivotal.xzhou.rps.webui.facade;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.JsonExpectationsHelper;
import rps.RPS;
import rps.doubles.repo.FakeRoundsRepo;

public class GamePlayTest {

    private JsonExpectationsHelper jsonAssert = new JsonExpectationsHelper();
    private Game game;
    
    @Before
    public void setUp() {
        game = new Game(new RPS(new FakeRoundsRepo()));
    }

    @Test
    public void givenPlayer1WillWin_whenPlayingARound_theGameShouldReturnThatPlayer1HasWon() throws Exception {
        jsonAssert.assertJsonEqual("{result:\"player 1 wins\"}", game.play("paper", "rock"));
    }

    @Test
    public void givenPlayer2WillWin_whenPlayingARound_theGameShouldReturnThatPlayer2HasWon() throws Exception {
        jsonAssert.assertJsonEqual("{result:\"player 2 wins\"}", game.play("rock", "paper"));
    }

    @Test
    public void givenTheRoundWillBeATie_whenPlayingARound_theGameShouldReturnThatItWasATie() throws Exception {
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

}