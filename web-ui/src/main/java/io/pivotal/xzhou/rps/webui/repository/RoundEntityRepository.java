package io.pivotal.xzhou.rps.webui.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoundEntityRepository extends JpaRepository<RoundEntity, Long> {
}
