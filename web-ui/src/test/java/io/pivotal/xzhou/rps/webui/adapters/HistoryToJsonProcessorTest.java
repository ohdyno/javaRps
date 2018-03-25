package io.pivotal.xzhou.rps.webui.adapters;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.JsonExpectationsHelper;
import rps.entity.Shapes;
import rps.entity.result.Result;
import rps.entity.Round;

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
                new Round(Shapes.Rock(), Shapes.Paper(), Result.Player2Wins()),
                new Round(Shapes.Scissors(), Shapes.Scissors(), Result.Tie())));

        jsonExpectationsHelper.assertJsonEqual(expected, actual);
    }
}