package rps.entity;

public enum Result {
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
    };

    public abstract <R> R print(ResultPrinter<R> printer);

    public static Result Player1Wins() {
        return Player1Wins;
    }

    public static Result Tie() {
        return Tie;
    }

    public static Result Player2Wins() {
        return Player2Wins;
    }
}
