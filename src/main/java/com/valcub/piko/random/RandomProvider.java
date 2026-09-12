package com.valcub.piko.random;

import java.util.List;

/** Request-scoped randomness used by every rendering stage. */
public interface RandomProvider {
    int nextInt(int bound);
    long nextLong();
    double nextDouble();

    default int nextInt(int origin, int bound) {
        if (origin >= bound) throw new IllegalArgumentException("origin must be less than bound");
        return origin + nextInt(bound - origin);
    }

    default double nextDouble(double origin, double bound) {
        if (!(origin < bound)) throw new IllegalArgumentException("origin must be less than bound");
        return origin + nextDouble() * (bound - origin);
    }

    default boolean nextBoolean() { return nextInt(2) == 0; }

    default <T> T pick(List<T> values) {
        if (values.isEmpty()) throw new IllegalArgumentException("Cannot pick from an empty list");
        return values.get(nextInt(values.size()));
    }
}
