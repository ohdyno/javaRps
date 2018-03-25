package rps.contract;

import rps.dependency.repository.RoundsRepository;
import rps.doubles.repo.FakeRoundsRepository;

public class FakeRoundsRepositoryContractTest extends RoundsRepositoryContractTest {
    @Override
    public RoundsRepository createRepo() {
        return new FakeRoundsRepository();
    }
}
