package com.valcub.piko.random;

import java.security.SecureRandom;

public final class SecureRandomProvider implements RandomProvider {
    /** One cryptographic generator per worker thread; no mutable generator is shared across requests. */
    private static final ThreadLocal<SecureRandom> RANDOM = ThreadLocal.withInitial(SecureRandom::new);

    private static SecureRandom random() {
        return RANDOM.get();
    }

    @Override public int nextInt(int bound) { return random().nextInt(bound); }
    @Override public long nextLong() { return random().nextLong(); }
    @Override public double nextDouble() { return random().nextDouble(); }
}
