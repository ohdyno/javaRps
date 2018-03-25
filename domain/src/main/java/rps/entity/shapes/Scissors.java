package rps.entity.shapes;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Scissors implements Shapes {
    private final int id = "scissors".hashCode();

    @Override
    public boolean beats(Shapes that) {
        return that instanceof Paper;
    }

    @Override
    public boolean tie(Shapes that) {
        return that instanceof Scissors;
    }

    @Override
    public <T> T print(ShapesPrinter<T> printer) {
        return printer.scissors();
    }
}
