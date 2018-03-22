package io.pivotal.xzhou.rps.webui.adapters;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.JsonExpectationsHelper;

public class PlayResultToJsonProcessorTest {

    private PlayResultToJsonProcessor processor;
    private JsonExpectationsHelper jsonExpectationsHelper;

    @Before
    public void setUp() {
        processor = new PlayResultToJsonProcessor();
        jsonExpectationsHelper = new JsonExpectationsHelper();
    }

    @Test
    public void itProcessesATieToAJsonObject() throws Exception {
        jsonExpectationsHelper.assertJsonEqual("{result:tie}", processor.tie());
    }

    @Test
    public void itProcessesPlayer1WinsToAJsonObject() throws Exception {
        jsonExpectationsHelper.assertJsonEqual("{result:'player 1 wins'}", processor.player1Wins());
    }

    @Test
    public void itProcessesPlayer2WinsToAJsonObject() throws Exception {
        jsonExpectationsHelper.assertJsonEqual("{result:'player 2 wins'}", processor.player2Wins());
    }
}
