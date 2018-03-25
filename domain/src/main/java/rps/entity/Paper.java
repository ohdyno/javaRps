package rps.entity;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Paper implements ThrowsTemporary {
    private final int id = "paper".hashCode();

    @Override
    public boolean beats(ThrowsTemporary that) {
        return that instanceof Rock;
    }

    @Override
    public boolean tie(ThrowsTemporary that) {
        return that instanceof Paper;
    }

    @Override
    public <T> T print(ThrowsPrinter<T> printer) {
        return printer.paper();
    }
}
