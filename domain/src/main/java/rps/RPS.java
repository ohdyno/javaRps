package rps;

import rps.dependency.processor.HistoryProcessorDelegate;
import rps.dependency.processor.PlayResultProcessorDelegate;
import rps.dependency.repository.RoundsRepository;
import rps.entity.Round;
import rps.entity.shapes.Shapes;
import rps.entity.result.Result;
import rps.exceptions.InvalidShape;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class RPS {
    private final RoundsRepository repo;

    public RPS(RoundsRepository repo) {
        this.repo = repo;
    }

    public <P> P playRound(String p1Throw, String p2Throw, PlayResultProcessorDelegate<P> ui) throws InvalidShape {
        validateThrows(p1Throw, p2Throw);
        return playRound(convertToThrow(p1Throw), convertToThrow(p2Throw), ui);
    }

    private void validateThrows(String... rpsThrows) throws InvalidShape {
        List<String> invalidThrows = new ArrayList<>();
        Function<String, Boolean> isInvalidThrow = (String rpsThrow) -> !(rpsThrow.equalsIgnoreCase("rock") || rpsThrow.equalsIgnoreCase("scissors") || rpsThrow.equalsIgnoreCase("paper"));
        Arrays.stream(rpsThrows).forEach((rpsThrow -> {
            if (isInvalidThrow.apply(rpsThrow)) {
                invalidThrows.add(rpsThrow);
            }
        }));

        if (!invalidThrows.isEmpty()) {
            throw new InvalidShape(invalidThrows);
        }
    }

    private Shapes convertToThrow(String rpsThrow) {
        if (rpsThrow.equalsIgnoreCase("rock")) {
            return Shapes.Rock();
        } else if (rpsThrow.equalsIgnoreCase("scissors")) {
            return Shapes.Scissors();
        } else {
            return Shapes.Paper();
        }
    }

    private <P> P playRound(Shapes p1Throw, Shapes p2Throw, PlayResultProcessorDelegate<P> ui) {
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
