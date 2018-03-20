package rps;

import rps.dependency.HistoryUI;
import rps.dependency.PlayUI;
import rps.dependency.RoundsRepo;
import rps.entity.Results;
import rps.entity.Round;
import rps.entity.Throws;

public class RPS implements RockPaperScissors {
    private final RoundsRepo repo;

    public RPS(RoundsRepo repo) {
        this.repo = repo;
    }

    public void playRound(Throws p1Throw, Throws p2Throw, PlayUI ui) {
        Results result;
        if (p1Throw.tie(p2Throw)) {
            ui.tie();
            result = Results.Tie;
        } else if (p1Throw.beats(p2Throw)) {
            ui.player1Wins();
            result = Results.Player1Wins;
        } else {
            ui.player2Wins();
            result = Results.Player2Wins;
        }
        Round round = new Round(p1Throw, p2Throw, result);
        repo.save(round);
    }

    public void getHistory(HistoryUI ui) {
        if (repo.isEmpty())
            ui.noRounds();
        else
            ui.roundsPlayed(repo.findAll());
    }
}
