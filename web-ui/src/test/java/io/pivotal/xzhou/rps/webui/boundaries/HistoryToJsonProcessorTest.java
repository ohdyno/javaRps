package io.pivotal.xzhou.rps.webui.boundaries;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.JsonExpectationsHelper;
import rps.entity.Results;
import rps.entity.Round;
import rps.entity.Throws;

import java.util.Arrays;

import static rps.entity.Results.*;
import static rps.entity.Throws.*;

public class HistoryToJsonProcessorTest {

    private JsonExpectationsHelper jsonExpectationsHelper;
    private HistoryToJsonProcessor subject;

    @Before
    public void setUp() throws Exception {
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
                new Round(Rock, Paper, Player2Wins),
                new Round(Scissors, Scissors, Tie)));

        jsonExpectationsHelper.assertJsonEqual(expected, actual);
    }
}