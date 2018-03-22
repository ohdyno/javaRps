package rps.dependency;

import rps.entity.Round;

import java.util.Collection;

public interface RoundsRepository {
    void save(Round round);

    boolean isEmpty();

    Collection<Round> findAll();
}
