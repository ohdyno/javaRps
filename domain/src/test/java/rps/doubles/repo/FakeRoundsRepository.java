package rps.doubles.repo;

import rps.dependency.repository.RoundsRepository;
import rps.entity.Round;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class FakeRoundsRepository implements RoundsRepository {
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
