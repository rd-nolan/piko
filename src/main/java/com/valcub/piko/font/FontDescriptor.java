package com.valcub.piko.font;

import java.util.Objects;

public record FontDescriptor(String family, FontWeight weight, String resourcePath) {
    public FontDescriptor {
        Objects.requireNonNull(family, "family");
        Objects.requireNonNull(weight, "weight");
        Objects.requireNonNull(resourcePath, "resourcePath");
        if (!resourcePath.startsWith("fonts/") || !resourcePath.endsWith(".ttf")) {
            throw new IllegalArgumentException("Font must be a classpath TTF under fonts/");
        }
    }
}
