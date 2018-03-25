package io.pivotal.xzhou.rps.webui.controller;

import io.pivotal.xzhou.rps.webui.adapters.PlayResultToJsonProcessor;
import io.pivotal.xzhou.rps.webui.dto.InvalidThrowsAsJson;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import rps.RPS;
import rps.exceptions.InvalidShape;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
class PlayController {

    private RPS game;

    PlayController(RPS game) {
        this.game = game;
    }

    @GetMapping(value = "/play/{p1Throw}/{p2Throw}", produces = APPLICATION_JSON_VALUE)
    public String play(@PathVariable String p1Throw,
                       @PathVariable String p2Throw) throws InvalidShape {
        return game.playRound(p1Throw, p2Throw, new PlayResultToJsonProcessor());
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler({InvalidShape.class})
    public String handleIllegalThrow(HttpServletRequest request, InvalidShape invalidShape) {
        return new InvalidThrowsAsJson(invalidShape.getInvalids()).toJson();
    }
}
