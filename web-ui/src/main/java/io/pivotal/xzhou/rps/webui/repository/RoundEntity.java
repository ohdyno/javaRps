package io.pivotal.xzhou.rps.webui.repository;

import rps.entity.Results;
import rps.entity.Round;
import rps.entity.Throws;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class RoundEntity {
    @Id
    @GeneratedValue
    private Long id;

    private Throws p1Throw;
    private Throws p2Throw;
    private Results result;

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