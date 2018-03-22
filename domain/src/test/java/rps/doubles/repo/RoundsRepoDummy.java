package rps.doubles.repo;

import rps.dependency.RoundsRepo;
import rps.entity.Round;

import java.util.Collection;
import java.util.Collections;

public class RoundsRepoDummy implements RoundsRepo {
    @Override
    public void save(Round round) {
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public Collection<Round> findAll() {
        return Collections.emptyList();
    }
}
