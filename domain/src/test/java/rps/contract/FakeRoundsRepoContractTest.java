package rps.contract;

import rps.dependency.RoundsRepo;
import rps.doubles.FakeRoundsRepo;

public class FakeRoundsRepoContractTest extends RoundsRepoContractTest {
    @Override
    public RoundsRepo createRepo() {
        return new FakeRoundsRepo();
    }
}
