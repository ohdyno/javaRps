package rps.entity;

public enum Throws {
    Rock {
        @Override
        public boolean beats(Throws that) {
            return that == Scissors;
        }

        @Override
        public boolean tie(Throws that) {
            return this == that;
        }

        @Override
        public <T> T print(ThrowsPrinter<T> printer) {
            return printer.rock();
        }
    },

    Paper {
        @Override
        public boolean beats(Throws that) {
            return that == Rock();
        }

        @Override
        public boolean tie(Throws that) {
            return this == that;
        }

        @Override
        public <T> T print(ThrowsPrinter<T> printer) {
            return printer.paper();
        }
    },

    Scissors {
        @Override
        public boolean beats(Throws that) {
            return that == Paper();
        }

        @Override
        public boolean tie(Throws that) {
            return this == that;
        }

        @Override
        public <T> T print(ThrowsPrinter<T> printer) {
            return printer.scissors();
        }
    };

    public abstract boolean beats(Throws that);

    public abstract boolean tie(Throws that);

    public abstract <T> T print(ThrowsPrinter<T> printer);

    public static Throws Rock() {
        return Rock;
    }

    public static Throws Paper() {
        return Paper;
    }
}
