package rps.entity;

public interface Throws {

    boolean beats(Throws that);

    boolean tie(Throws that);

    <T> T print(ThrowsPrinter<T> printer);

    static Throws Rock() {
        return new Rock();
    }

    static Throws Paper() {
        return new Paper();
    }

    static Throws Scissors() {
        return new Scissors();
    }
}
