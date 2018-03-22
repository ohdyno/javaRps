package io.pivotal.xzhou.rps.webui.facade;

import org.junit.Test;
import org.springframework.test.util.JsonExpectationsHelper;
import rps.RPS;
import rps.doubles.repo.FakeRoundsRepo;

public class GameHistoryTest {
    private JsonExpectationsHelper jsonAssert = new JsonExpectationsHelper();

    @Test
    public void givenNoRoundsHaveBeenPlayed_whenGettingHistoryFromGame_thenTheRoundsPlayedShouldBeEmpty() throws Exception {
        Game game = new Game(new RPS(new FakeRoundsRepo()));
        jsonAssert.assertJsonEqual("{rounds:[]}", game.getHistory());
    }

    @Test
    public void givenRoundsHaveBeenPlayed_whenGettingHistoryFromGame_thenTheRoundsPlayedShouldContainAllRoundsPlayed() throws Exception {
        String expected = "{rounds:[" +
                "{p1:rock,p2:paper,result:\"player 2 wins\"}," +
                "{p1:scissors,p2:scissors,result:tie}" +
                "]}";
        Game game = new Game(new RPS(new FakeRoundsRepo()));

        game.play("rock", "paper");
        game.play("scissors", "scissors");

        jsonAssert.assertJsonEqual(expected, game.getHistory());
    }
}