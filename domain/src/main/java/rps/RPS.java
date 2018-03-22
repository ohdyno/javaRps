package rps;

import rps.dependency.HistoryProcessorDelegate;
import rps.dependency.PlayResultProcessorDelegate;
import rps.dependency.RoundsRepo;
import rps.entity.Results;
import rps.entity.Round;
import rps.entity.Throws;

public class RPS implements RockPaperScissors {
    private final RoundsRepo repo;

    public RPS(RoundsRepo repo) {
        this.repo = repo;
    }

    @Override
    public <P> P playRound(Throws p1Throw, Throws p2Throw, PlayResultProcessorDelegate<P> ui) {
        if (p1Throw.tie(p2Throw)) {
            repo.save(new Round(p1Throw, p2Throw, Results.Tie));
            return ui.tie();
        }

        if (p1Throw.beats(p2Throw)) {
            repo.save(new Round(p1Throw, p2Throw, Results.Player1Wins));
            return ui.player1Wins();
        }

        repo.save(new Round(p1Throw, p2Throw, Results.Player2Wins));
        return ui.player2Wins();
    }

    @Override
    public <H> H getHistory(HistoryProcessorDelegate<H> ui) {
        if (repo.isEmpty())
            return ui.noRounds();
        else
            return ui.roundsPlayed(repo.findAll());
    }
}
