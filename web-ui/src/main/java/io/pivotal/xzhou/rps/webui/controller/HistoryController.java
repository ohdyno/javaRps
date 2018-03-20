package io.pivotal.xzhou.rps.webui.controller;

import io.pivotal.xzhou.rps.webui.facade.Game;
import io.pivotal.xzhou.rps.webui.boundaries.JpaToRoundsRepoAdapter;
import io.pivotal.xzhou.rps.webui.repository.RoundEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import rps.RPS;

@RestController
public class HistoryController {

    private final RoundEntityRepository jpaRepo;

    @Autowired
    public HistoryController(RoundEntityRepository jpaRepo) {
        this.jpaRepo = jpaRepo;
    }

    @GetMapping("/history")
    public String getHistory() {
        Game game = new Game(new RPS(new JpaToRoundsRepoAdapter(jpaRepo)));
        return game.getHistory().toJson();
    }
}
