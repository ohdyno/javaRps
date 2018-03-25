package rps.doubles.repo;

import rps.dependency.repository.RoundsRepository;
import rps.entity.Round;

import java.util.Collection;
import java.util.Collections;

public class RoundsRepositoryDummy implements RoundsRepository {
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
