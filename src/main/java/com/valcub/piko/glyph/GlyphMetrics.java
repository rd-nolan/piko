package com.valcub.piko.glyph;

public record GlyphMetrics(double x, double y, double width, double height) {
    public boolean isEmpty() {
        return width <= 0 || height <= 0;
    }
}
