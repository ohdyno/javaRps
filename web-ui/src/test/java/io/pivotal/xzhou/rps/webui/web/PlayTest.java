package io.pivotal.xzhou.rps.webui.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@AutoConfigureMockMvc
@DirtiesContext
public class PlayTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void whenPlayingARound_thenSendTheThrowsToRPS_andReturnTheResultAsJson() throws Exception {
        String p2Throw = "scissors";
        String p1Throw = "rock";
        String result = "player 1 wins";

        mvc.perform(get("/play/" + p1Throw + "/" + p2Throw))
                .andExpect(content().json(
                        "{" +
                                "result:" + "'" + result + "'" +
                                "}"
                ));

    }

    @Test
    public void givenAnInvalidThrow_whenPlayingARound_thenReturnAnErrorWithBodyIndicatingWhichThrowWasInvalid() throws Exception {
        String p1Throw = "sailboat";
        String p2Throw = "somethingElseInvalid";

        mvc.perform(get("/play/" + p1Throw + "/" + p2Throw))
                .andExpect(
                        content().json(
                                "{" +
                                        "invalid_throws:" + "['" + p1Throw + "'," + p2Throw + "]" +
                                        "}"
                        ))
                .andExpect(status().isBadRequest());
    }
}
