package io.pivotal.xzhou.rps.webui.controller;

import io.pivotal.xzhou.rps.webui.adapters.HistoryToJsonProcessor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import rps.RPS;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class HistoryController {

    private RPS game;

    public HistoryController(RPS game) {
        this.game = game;
    }

    @GetMapping(value = "/history", produces = APPLICATION_JSON_VALUE)
    public String getHistory() {
        return game.getHistory(new HistoryToJsonProcessor());
    }
}
