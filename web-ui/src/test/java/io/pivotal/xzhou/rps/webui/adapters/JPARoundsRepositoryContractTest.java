package io.pivotal.xzhou.rps.webui.adapters;

import io.pivotal.xzhou.rps.webui.repository.RoundEntityRepository;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import rps.contract.RoundsRepositoryContractTest;
import rps.dependency.RoundsRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class JPARoundsRepositoryContractTest extends RoundsRepositoryContractTest {

    @Autowired
    private RoundEntityRepository repository;

    @Override
    public RoundsRepository createRepo() {
        return new JpaToRoundsRepositoryAdapter(repository);
    }
}