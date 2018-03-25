package rps.entity;

public interface ThrowsTemporary {

    boolean beats(ThrowsTemporary that);

    boolean tie(ThrowsTemporary that);

    <T> T print(ThrowsPrinter<T> printer);

    static ThrowsTemporary Rock() {
        return new Rock();
    }

    static ThrowsTemporary Paper() {
        return new Paper();
    }

    static ThrowsTemporary Scissors() {
        return new Scissors();
    }
}
