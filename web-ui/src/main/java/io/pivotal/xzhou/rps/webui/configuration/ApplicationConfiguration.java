package io.pivotal.xzhou.rps.webui.configuration;

import io.pivotal.xzhou.rps.webui.adapters.JpaToRoundsRepositoryAdapter;
import io.pivotal.xzhou.rps.webui.repository.RoundEntityRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rps.RPS;

@Configuration
public class ApplicationConfiguration {
    private RoundEntityRepository jpaRepo;

    public ApplicationConfiguration(RoundEntityRepository jpaRepo) {
        this.jpaRepo = jpaRepo;
    }

    @Bean
    public RPS constructRPS() {
        return new RPS(new JpaToRoundsRepositoryAdapter(jpaRepo));
    }
}
