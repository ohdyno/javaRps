package io.pivotal.xzhou.rps.webui.adapters;

import io.pivotal.xzhou.rps.webui.adapters.JpaToRoundsRepoAdapter;
import io.pivotal.xzhou.rps.webui.repository.RoundEntityRepository;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import rps.contract.RoundsRepoContractTest;
import rps.dependency.RoundsRepo;

@RunWith(SpringRunner.class)
@DataJpaTest
public class JPARoundsRepoContractTest extends RoundsRepoContractTest {

    @Autowired
    private RoundEntityRepository repository;

    @Override
    public RoundsRepo createRepo() {
        return new JpaToRoundsRepoAdapter(repository);
    }
}