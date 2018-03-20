package rps.contract;

import rps.dependency.RoundsRepo;
import rps.doubles.history.FakeRoundsRepo;

public class FakeRoundsRepoContractTest extends RoundsRepoContractTest {
    @Override
    public RoundsRepo createRepo() {
        return new FakeRoundsRepo();
    }
}
