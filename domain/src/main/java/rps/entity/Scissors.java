package rps.entity;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Scissors implements ThrowsTemporary {
    private final int id = "scissors".hashCode();

    @Override
    public boolean beats(ThrowsTemporary that) {
        return that instanceof Paper;
    }

    @Override
    public boolean tie(ThrowsTemporary that) {
        return that instanceof Scissors;
    }

    @Override
    public <T> T print(ThrowsPrinter<T> printer) {
        return printer.scissors();
    }
}
