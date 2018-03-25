package rps.entity;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Rock implements ThrowsTemporary {
    private final int id = "rock".hashCode();

    @Override
    public boolean beats(ThrowsTemporary that) {
        return that instanceof Scissors;
    }

    @Override
    public boolean tie(ThrowsTemporary that) {
        return that instanceof Rock;
    }

    @Override
    public <T> T print(ThrowsPrinter<T> printer) {
        return printer.rock();
    }
}
