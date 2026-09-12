package com.valcub.piko.layout;

/** Placement slot for one character in a request-local coordinate system. */
public record CharacterLayout(int index, char value, double centerX, double centerY, double slotWidth) {
    public CharacterLayout {
        if (index < 0) {
            throw new IllegalArgumentException("index must be non-negative");
        }
        if (!Double.isFinite(centerX) || !Double.isFinite(centerY) || !Double.isFinite(slotWidth)
                || !(slotWidth > 0)) {
            throw new IllegalArgumentException("layout coordinates must be finite and slotWidth must be positive");
        }
    }
}
