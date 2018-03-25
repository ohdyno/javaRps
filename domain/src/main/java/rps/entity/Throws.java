package rps.entity;

public enum Throws implements ThrowsTemporary {
    Rock {
        @Override
        public boolean beats(ThrowsTemporary that) {
            return that == ThrowsTemporary.Scissors();
        }

        @Override
        public boolean tie(ThrowsTemporary that) {
            return this == that;
        }

        @Override
        public <T> T print(ThrowsPrinter<T> printer) {
            return printer.rock();
        }
    },

    Paper {
        @Override
        public boolean beats(ThrowsTemporary that) {
            return that == ThrowsTemporary.Rock();
        }

        @Override
        public boolean tie(ThrowsTemporary that) {
            return this == that;
        }

        @Override
        public <T> T print(ThrowsPrinter<T> printer) {
            return printer.paper();
        }
    },

    Scissors {
        @Override
        public boolean beats(ThrowsTemporary that) {
            return that == ThrowsTemporary.Paper();
        }

        @Override
        public boolean tie(ThrowsTemporary that) {
            return this == that;
        }

        @Override
        public <T> T print(ThrowsPrinter<T> printer) {
            return printer.scissors();
        }
    }
}
