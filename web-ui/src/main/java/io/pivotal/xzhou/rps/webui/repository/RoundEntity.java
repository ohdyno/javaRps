package io.pivotal.xzhou.rps.webui.repository;

import rps.entity.shapes.Shapes;
import rps.entity.result.Result;
import rps.entity.Round;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class RoundEntity {
    @Id
    @GeneratedValue
    private Long id;

    private Shapes p1Throw;
    private Shapes p2Throw;
    private Result result;

    protected RoundEntity() {
    }

    public RoundEntity(Round round) {
        p1Throw = round.getP1Throw();
        p2Throw = round.getP2Throw();
        result = round.getResult();
    }

    public Round toRound() {
        return new Round(p1Throw, p2Throw, result);
    }
}
