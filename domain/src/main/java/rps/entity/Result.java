package rps.entity;

public enum Result implements ResultTemporary {
    Player1Wins {
        @Override
        public <R> R print(ResultPrinter<R> printer) {
            return printer.player1Wins();
        }
    },
    Player2Wins {
        @Override
        public <R> R print(ResultPrinter<R> printer) {
            return printer.player2Wins();
        }
    },
    Tie {
        @Override
        public <R> R print(ResultPrinter<R> printer) {
            return printer.tie();
        }
    }
}
