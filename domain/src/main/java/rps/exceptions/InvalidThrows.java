package rps.exceptions;

import java.util.Collection;

public class InvalidThrows extends Exception {
    private final Collection<String> invalidThrows;

    public InvalidThrows(Collection<String> invalidThrows) {
        this.invalidThrows = invalidThrows;
    }

    public Collection<String> getInvalidThrows() {
        return invalidThrows;
    }
}
