package rps.entity;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Rock implements Shapes {
    private final int id = "rock".hashCode();

    @Override
    public boolean beats(Shapes that) {
        return that instanceof Scissors;
    }

    @Override
    public boolean tie(Shapes that) {
        return that instanceof Rock;
    }

    @Override
    public <T> T print(ShapesPrinter<T> printer) {
        return printer.rock();
    }
}
