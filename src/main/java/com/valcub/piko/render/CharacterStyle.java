package com.valcub.piko.render;

import com.valcub.piko.font.FontDescriptor;

import java.util.Objects;
import java.util.regex.Pattern;

/** Validated visual parameters for a single glyph path. */
public record CharacterStyle(FontDescriptor font, float fontSize, String fill, double rotationDegrees,
                             double scaleX, double scaleY, double opacity,
                             double centerX, double centerY) {
    private static final Pattern COLOR = Pattern.compile("#[0-9A-Fa-f]{6}([0-9A-Fa-f]{2})?");

    public CharacterStyle {
        Objects.requireNonNull(font, "font");
        Objects.requireNonNull(fill, "fill");
        if (!(fontSize > 0) || !Float.isFinite(fontSize)) {
            throw new IllegalArgumentException("fontSize must be finite and positive");
        }
        if (!COLOR.matcher(fill).matches()) {
            throw new IllegalArgumentException("fill must be a hexadecimal color");
        }
        if (!Double.isFinite(rotationDegrees) || !Double.isFinite(scaleX) || !Double.isFinite(scaleY)
                || !Double.isFinite(opacity) || !Double.isFinite(centerX) || !Double.isFinite(centerY)
                || !(scaleX > 0) || !(scaleY > 0) || opacity < 0 || opacity > 1) {
            throw new IllegalArgumentException("character style values must be finite and in range");
        }
    }
}
