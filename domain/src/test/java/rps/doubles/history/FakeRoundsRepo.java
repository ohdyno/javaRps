package rps.doubles.history;

import rps.entity.Round;
import rps.dependency.RoundsRepo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class FakeRoundsRepo implements RoundsRepo {
    private List<Round> rounds = new ArrayList<>();

    @Override
    public void save(Round round) {
        rounds.add(round);
    }

    @Override
    public boolean isEmpty() {
        return rounds.isEmpty();
    }

    @Override
    public Collection<Round> findAll() {
        return rounds;
    }
}
