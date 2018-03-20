package rps.entity;

import lombok.Value;

@Value
public class Round {
    private final Throws p1Throw;
    private final Throws p2Throw;
    private final Results result;
}
