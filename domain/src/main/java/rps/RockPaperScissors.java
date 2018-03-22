package rps;

import rps.dependency.HistoryUI;
import rps.dependency.PlayUI;
import rps.entity.Throws;

public interface RockPaperScissors {
    <P> P playRound(Throws p1Throw, Throws p2Throw, PlayUI<P> ui);

    <H> H getHistory(HistoryUI<H> ui);
}
