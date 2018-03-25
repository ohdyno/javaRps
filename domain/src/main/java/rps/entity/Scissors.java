package rps.entity;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Scissors implements Throws {
    private final int id = "scissors".hashCode();

    @Override
    public boolean beats(Throws that) {
        return that instanceof Paper;
    }

    @Override
    public boolean tie(Throws that) {
        return that instanceof Scissors;
    }

    @Override
    public <T> T print(ThrowsPrinter<T> printer) {
        return printer.scissors();
    }
}
