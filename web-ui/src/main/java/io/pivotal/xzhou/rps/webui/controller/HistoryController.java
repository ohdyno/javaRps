package io.pivotal.xzhou.rps.webui.controller;

import io.pivotal.xzhou.rps.webui.facade.Game;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class HistoryController {

    private Game game;

    public HistoryController(Game game) {
        this.game = game;
    }

    @GetMapping(value = "/history", produces = APPLICATION_JSON_VALUE)
    public String getHistory() {
        return game.getHistory();
    }
}
