package io.pivotal.xzhou.rps.webui.facade;

import org.json.JSONException;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import rps.RockPaperScissors;
import rps.entity.Round;
import rps.entity.Throws;
import rps.dependency.HistoryUI;
import rps.dependency.PlayUI;

import java.util.Arrays;
import java.util.List;

import static rps.entity.Results.Player2Wins;
import static rps.entity.Results.Tie;
import static rps.entity.Throws.*;

public class GameHistoryTest {
    @Test
    public void givenNoRoundsHaveBeenPlayed_whenGettingHistoryFromGame_thenTheRoundsPlayedShouldBeEmpty() throws JSONException {
        RockPaperScissors rps = noRoundsPlayedStub();
        Game game = new Game(rps);
        JSONAssert.assertEquals("{rounds:[]}", game.getHistory().toJson(), false);
    }

    private RockPaperScissors noRoundsPlayedStub() {
        return new RockPaperScissors() {
            @Override
            public void playRound(Throws p1Throw, Throws p2Throw, PlayUI ui) {

            }

            @Override
            public void getHistory(HistoryUI ui) {
                ui.noRounds();
            }
        };
    }

    @Test
    public void givenRoundsHaveBeenPlayed_whenGettingHistoryFromGame_thenTheRoundsPlayedShouldContainAllRoundsPlayed() throws JSONException {
        List<Round> roundsPlayed = Arrays.asList(
                new Round(Rock, Paper, Player2Wins),
                new Round(Scissors, Scissors, Tie)
        );
        RockPaperScissors rps = multipleRoundsPlayedStub(roundsPlayed);
        String expected = "{rounds:[" +
                "{p1:rock,p2:paper,result:\"player 2 wins\"}," +
                "{p1:scissors,p2:scissors,result:tie}" +
                "]}";
        Game game = new Game(rps);
        JSONAssert.assertEquals(expected, game.getHistory().toJson(), false);
    }

    private RockPaperScissors multipleRoundsPlayedStub(List<Round> roundsPlayed) {
        return new RockPaperScissors() {
            @Override
            public void playRound(Throws p1Throw, Throws p2Throw, PlayUI ui) {

            }

            @Override
            public void getHistory(HistoryUI ui) {
                ui.roundsPlayed(roundsPlayed);
            }
        };
    }
}