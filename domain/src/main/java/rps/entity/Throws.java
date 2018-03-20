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
    },

    Paper {
        @Override
        public boolean beats(Throws that) {
            return that == Rock;
        }

        @Override
        public boolean tie(Throws that) {
            return this == that;
        }
    },

    Scissors {
        @Override
        public boolean beats(Throws that) {
            return that == Paper;
        }

        @Override
        public boolean tie(Throws that) {
            return this == that;
        }
    };

    public abstract boolean beats(Throws that);

    public abstract boolean tie(Throws that);
}
