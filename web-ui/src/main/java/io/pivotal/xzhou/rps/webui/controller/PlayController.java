package io.pivotal.xzhou.rps.webui.controller;

import io.pivotal.xzhou.rps.webui.boundaries.JpaToRoundsRepoAdapter;
import io.pivotal.xzhou.rps.webui.dto.InvalidThrowsAsJson;
import io.pivotal.xzhou.rps.webui.facade.Game;
import io.pivotal.xzhou.rps.webui.repository.RoundEntityRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import rps.RPS;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
class PlayController {

    private RoundEntityRepository jpaRepo;

    PlayController(RoundEntityRepository jpaRepo) {
        this.jpaRepo = jpaRepo;
    }

    @GetMapping(value = "/play/{p1Throw}/{p2Throw}", produces = APPLICATION_JSON_VALUE)
    public String play(@PathVariable String p1Throw,
                       @PathVariable String p2Throw) {

        Game game = new Game(new RPS(new JpaToRoundsRepoAdapter(jpaRepo)));
        return game.play(p1Throw, p2Throw).toJson();
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler({Game.IllegalThrowException.class})
    public String handleIllegalThrow(HttpServletRequest request, Game.IllegalThrowException exception) {
        return new InvalidThrowsAsJson(exception.getInvalidThrows()).toJson();
    }
}