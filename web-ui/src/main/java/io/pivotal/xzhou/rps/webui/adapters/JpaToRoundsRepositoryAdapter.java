package io.pivotal.xzhou.rps.webui.adapters;

import io.pivotal.xzhou.rps.webui.repository.RoundEntity;
import io.pivotal.xzhou.rps.webui.repository.RoundEntityRepository;
import rps.entity.Round;
import rps.dependency.RoundsRepository;

import java.util.Collection;
import java.util.stream.Collectors;

public class JpaToRoundsRepositoryAdapter implements RoundsRepository {

    private final RoundEntityRepository jpaRepo;

    public JpaToRoundsRepositoryAdapter(RoundEntityRepository jpaRepo) {
        this.jpaRepo = jpaRepo;
    }

    @Override
    public void save(Round round) {
        jpaRepo.save(new RoundEntity(round));
    }

    @Override
    public boolean isEmpty() {
        return jpaRepo.count() == 0;
    }

    @Override
    public Collection<Round> findAll() {
        return jpaRepo.findAll()
                .stream()
                .map(RoundEntity::toRound)
                .collect(Collectors.toList());
    }
}
