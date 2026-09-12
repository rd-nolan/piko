package com.valcub.piko.captcha;

/** Controls the amount of decorative background and foreground noise. */
public enum NoiseLevel {
    NONE(0),
    LOW(8),
    NORMAL(16),
    HIGH(28);

    private final int decorationCount;

    NoiseLevel(int decorationCount) {
        this.decorationCount = decorationCount;
    }

    public int decorationCount() {
        return decorationCount;
    }
}
