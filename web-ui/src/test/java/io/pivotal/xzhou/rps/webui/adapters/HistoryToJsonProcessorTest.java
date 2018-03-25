package io.pivotal.xzhou.rps.webui.adapters;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.JsonExpectationsHelper;
import rps.entity.Result;
import rps.entity.Round;
import rps.entity.Throws;

import java.util.Arrays;

public class HistoryToJsonProcessorTest {

    private JsonExpectationsHelper jsonExpectationsHelper;
    private HistoryToJsonProcessor subject;

    @Before
    public void setUp() {
        jsonExpectationsHelper = new JsonExpectationsHelper();
        subject = new HistoryToJsonProcessor();
    }

    @Test
    public void itProcessNoRoundsToAJsonObjectWithAnEmptyArray() throws Exception {
        jsonExpectationsHelper.assertJsonEqual("{rounds:[]}", subject.noRounds());
    }

    @Test
    public void itProcessMultipleRoundsPlayedToAJsonObjectWithArrayThatContainsAllRounds() throws Exception {
        String expected = "{rounds:[" +
                "{p1:rock,p2:paper,result:\"player 2 wins\"}," +
                "{p1:scissors,p2:scissors,result:tie}" +
                "]}";

        String actual = subject.roundsPlayed(Arrays.asList(
                new Round(Throws.Rock, Throws.Paper, Result.Player2Wins()),
                new Round(Throws.Scissors, Throws.Scissors, Result.Tie())));

        jsonExpectationsHelper.assertJsonEqual(expected, actual);
    }
}