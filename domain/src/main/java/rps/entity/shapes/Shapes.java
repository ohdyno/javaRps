package rps.entity.shapes;

import rps.dependency.printer.ShapesPrinter;

public interface Shapes {

    boolean beats(Shapes that);

    boolean tie(Shapes that);

    <T> T print(ShapesPrinter<T> printer);

    static Shapes Rock() {
        return new Rock();
    }

    static Shapes Paper() {
        return new Paper();
    }

    static Shapes Scissors() {
        return new Scissors();
    }
}
