package io.pivotal.xzhou.rps.webui.facade;

import org.junit.Test;
import org.springframework.test.util.JsonExpectationsHelper;
import rps.RockPaperScissors;
import rps.dependency.HistoryUI;
import rps.dependency.PlayUI;
import rps.entity.Round;
import rps.entity.Throws;

import java.util.Arrays;
import java.util.List;

import static rps.entity.Results.Player2Wins;
import static rps.entity.Results.Tie;
import static rps.entity.Throws.*;

public class GameHistoryTest {
    private JsonExpectationsHelper jsonAssert = new JsonExpectationsHelper();

    @Test
    public void givenNoRoundsHaveBeenPlayed_whenGettingHistoryFromGame_thenTheRoundsPlayedShouldBeEmpty() throws Exception {
        RockPaperScissors rps = new NoRoundsPlayedStub();
        Game game = new Game(rps);
        jsonAssert.assertJsonEqual("{rounds:[]}", game.getHistory());
    }

    @Test
    public void givenRoundsHaveBeenPlayed_whenGettingHistoryFromGame_thenTheRoundsPlayedShouldContainAllRoundsPlayed() throws Exception {
        List<Round> roundsPlayed = Arrays.asList(
                new Round(Rock, Paper, Player2Wins),
                new Round(Scissors, Scissors, Tie)
        );
        RockPaperScissors rps = new MultipleRoundsPlayedStub(roundsPlayed);
        String expected = "{rounds:[" +
                "{p1:rock,p2:paper,result:\"player 2 wins\"}," +
                "{p1:scissors,p2:scissors,result:tie}" +
                "]}";
        Game game = new Game(rps);
        jsonAssert.assertJsonEqual(expected, game.getHistory());
    }

    private class NoRoundsPlayedStub implements RockPaperScissors {
        @Override
        public void playRound(Throws p1Throw, Throws p2Throw, PlayUI ui) {

        }

        @Override
        public void getHistory(HistoryUI ui) {
            ui.noRounds();
        }
    }

    private class MultipleRoundsPlayedStub implements RockPaperScissors {
        private final List<Round> roundsPlayed;

        MultipleRoundsPlayedStub(List<Round> roundsPlayed) {
            this.roundsPlayed = roundsPlayed;
        }

        @Override
        public void playRound(Throws p1Throw, Throws p2Throw, PlayUI ui) {

        }

        @Override
        public void getHistory(HistoryUI ui) {
            ui.roundsPlayed(roundsPlayed);
        }
    }
}