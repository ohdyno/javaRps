package rps;

import rps.dependency.HistoryProcessorDelegate;
import rps.dependency.PlayResultProcessorDelegate;
import rps.dependency.RoundsRepository;
import rps.entity.result.Result;
import rps.entity.Round;
import rps.entity.Throws;
import rps.exceptions.InvalidThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static rps.entity.Throws.*;

public class RPS {
    private final RoundsRepository repo;

    public RPS(RoundsRepository repo) {
        this.repo = repo;
    }

    public <P> P playRound(String p1Throw, String p2Throw, PlayResultProcessorDelegate<P> ui) throws InvalidThrows {
        validateThrows(p1Throw, p2Throw);
        return playRound(convertToThrow(p1Throw), convertToThrow(p2Throw), ui);
    }

    private void validateThrows(String... rpsThrows) throws InvalidThrows {
        List<String> invalidThrows = new ArrayList<>();
        Function<String, Boolean> isInvalidThrow = (String rpsThrow) -> !(rpsThrow.equalsIgnoreCase("rock") || rpsThrow.equalsIgnoreCase("scissors") || rpsThrow.equalsIgnoreCase("paper"));
        Arrays.stream(rpsThrows).forEach((rpsThrow -> {
            if (isInvalidThrow.apply(rpsThrow)) {
                invalidThrows.add(rpsThrow);
            }
        }));

        if (!invalidThrows.isEmpty()) {
            throw new InvalidThrows(invalidThrows);
        }
    }

    private Throws convertToThrow(String rpsThrow) {
        if (rpsThrow.equalsIgnoreCase("rock")) {
            return Rock;
        } else if (rpsThrow.equalsIgnoreCase("scissors")) {
            return Scissors;
        } else {
            return Paper;
        }
    }

    private <P> P playRound(Throws p1Throw, Throws p2Throw, PlayResultProcessorDelegate<P> ui) {
        if (p1Throw.tie(p2Throw)) {
            repo.save(new Round(p1Throw, p2Throw, Result.Tie()));
            return ui.tie();
        }

        if (p1Throw.beats(p2Throw)) {
            repo.save(new Round(p1Throw, p2Throw, Result.Player1Wins()));
            return ui.player1Wins();
        }

        repo.save(new Round(p1Throw, p2Throw, Result.Player2Wins()));
        return ui.player2Wins();
    }

    public <H> H getHistory(HistoryProcessorDelegate<H> ui) {
        if (repo.isEmpty())
            return ui.noRounds();
        else
            return ui.roundsPlayed(repo.findAll());
    }
}
