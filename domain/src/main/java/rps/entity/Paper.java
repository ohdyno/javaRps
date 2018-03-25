package rps.entity;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Paper implements Shapes {
    private final int id = "paper".hashCode();

    @Override
    public boolean beats(Shapes that) {
        return that instanceof Rock;
    }

    @Override
    public boolean tie(Shapes that) {
        return that instanceof Paper;
    }

    @Override
    public <T> T print(ShapesPrinter<T> printer) {
        return printer.paper();
    }
}
