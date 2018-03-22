package rps;

import rps.dependency.HistoryProcessorDelegate;
import rps.dependency.PlayResultProcessorDelegate;
import rps.entity.Throws;

public interface RockPaperScissors {
    <P> P playRound(Throws p1Throw, Throws p2Throw, PlayResultProcessorDelegate<P> ui);

    <H> H getHistory(HistoryProcessorDelegate<H> ui);
}
