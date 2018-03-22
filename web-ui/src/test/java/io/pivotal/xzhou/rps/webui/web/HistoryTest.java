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

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@AutoConfigureMockMvc
@DirtiesContext
public class HistoryTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void whenGettingTheHistoryFromTheGame_thenTheResultIsReturnedAsProperJsonString() throws Exception {
        mvc.perform(get("/play/rock/scissors"));
        mvc.perform(get("/history"))
                .andExpect(content().json("{" +
                        "rounds:" + "[{'p1':'rock','p2':'scissors','result':'player 1 wins'}]" +
                        "}"));
    }
}
