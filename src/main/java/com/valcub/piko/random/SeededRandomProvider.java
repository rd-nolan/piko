package com.valcub.piko.random;

import java.util.SplittableRandom;

public final class SeededRandomProvider implements RandomProvider {
    private final SplittableRandom random;

    public SeededRandomProvider(long seed) { this.random = new SplittableRandom(seed); }
    @Override public int nextInt(int bound) { return random.nextInt(bound); }
    @Override public long nextLong() { return random.nextLong(); }
    @Override public double nextDouble() { return random.nextDouble(); }
}
