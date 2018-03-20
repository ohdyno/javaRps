package rps;

import rps.dependency.HistoryUI;
import rps.dependency.PlayUI;
import rps.entity.Throws;

public interface RockPaperScissors {
    public void playRound(Throws p1Throw, Throws p2Throw, PlayUI ui);

    public void getHistory(HistoryUI ui);
}
