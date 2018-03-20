package io.pivotal.xzhou.rps.webui.controller;

import io.pivotal.xzhou.rps.webui.dto.InvalidThrowsAsJson;
import io.pivotal.xzhou.rps.webui.facade.Game;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
class PlayController {

    private Game game;

    PlayController(Game game) {
        this.game = game;
    }

    @GetMapping(value = "/play/{p1Throw}/{p2Throw}", produces = APPLICATION_JSON_VALUE)
    public String play(@PathVariable String p1Throw,
                       @PathVariable String p2Throw) {
        return game.play(p1Throw, p2Throw);
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler({Game.InvalidThrow.class})
    public String handleIllegalThrow(HttpServletRequest request, Game.InvalidThrow exception) {
        return new InvalidThrowsAsJson(exception.getInvalidThrows()).toJson();
    }
}
