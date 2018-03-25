package rps.exceptions;

import java.util.Collection;

public class InvalidShape extends Exception {
    private final Collection<String> invalids;

    public InvalidShape(Collection<String> invalids) {
        this.invalids = invalids;
    }

    public Collection<String> getInvalids() {
        return invalids;
    }
}
