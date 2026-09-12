package com.valcub.piko.svg;

import java.util.Objects;

/** Small value object for validated SVG path data. */
public record SvgPath(String data) {
    public SvgPath {
        Objects.requireNonNull(data, "data");
        if (data.isBlank() || data.indexOf('<') >= 0 || data.indexOf('>') >= 0) {
            throw new IllegalArgumentException("SVG path data must be non-empty and contain no markup");
        }
    }

    public static SvgPath of(String data) {
        return new SvgPath(data);
    }
}
