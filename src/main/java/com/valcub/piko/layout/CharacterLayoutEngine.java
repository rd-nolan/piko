package com.valcub.piko.layout;

import com.valcub.piko.random.RandomProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/** Computes stable, gently varied character slots without shared mutable state. */
public final class CharacterLayoutEngine {
    public List<CharacterLayout> layout(String code, int width, int height, RandomProvider random) {
        Objects.requireNonNull(code, "code");
        Objects.requireNonNull(random, "random");
        if (code.isEmpty()) {
            throw new IllegalArgumentException("code cannot be empty");
        }
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("layout dimensions must be positive");
        }
        double padding = Math.min(24.0, Math.max(14.0, width * 0.07));
        double slotWidth = (width - padding * 2.0) / code.length();
        if (!(slotWidth > 0)) {
            throw new IllegalArgumentException("captcha width is too small for the code");
        }
        double jitterX = Math.min(5.0, slotWidth * 0.16);
        double jitterY = Math.min(4.0, height * 0.045);
        List<CharacterLayout> result = new ArrayList<>(code.length());
        for (int index = 0; index < code.length(); index++) {
            double centerX = padding + slotWidth * (index + 0.5) + random.nextDouble(-jitterX, jitterX);
            double centerY = height * 0.53 + random.nextDouble(-jitterY, jitterY);
            result.add(new CharacterLayout(index, code.charAt(index), centerX, centerY, slotWidth));
        }
        return List.copyOf(result);
    }
}
