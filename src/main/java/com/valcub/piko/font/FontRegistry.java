package com.valcub.piko.font;

import java.awt.Font;
import java.awt.font.TextAttribute;
import java.io.UncheckedIOException;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/** Thread-safe process-wide font cache. Each classpath resource is parsed at most once per registry. */
public final class FontRegistry {
    private static final FontRegistry DEFAULT = new FontRegistry(new ClasspathFontProvider());

    private final FontProvider provider;
    private final ConcurrentMap<String, Font> cache = new ConcurrentHashMap<>();

    public FontRegistry(FontProvider provider) {
        this.provider = Objects.requireNonNull(provider, "provider");
    }

    public static FontRegistry defaultRegistry() {
        return DEFAULT;
    }

    public Font baseFont(FontDescriptor descriptor) {
        Objects.requireNonNull(descriptor, "descriptor");
        return cache.computeIfAbsent(descriptor.resourcePath(), resourcePath -> {
            try {
                return provider.load(descriptor).font();
            } catch (java.io.IOException exception) {
                throw new UncheckedIOException(exception);
            }
        });
    }

    public Font derive(FontDescriptor descriptor, float size) {
        if (!(size > 0) || !Float.isFinite(size)) {
            throw new IllegalArgumentException("Font size must be finite and positive");
        }
        Font weighted = baseFont(descriptor).deriveFont(Map.of(
                TextAttribute.WEIGHT, textWeight(descriptor.weight())));
        return weighted.deriveFont(size);
    }

    private static float textWeight(FontWeight weight) {
        return switch (weight) {
            case REGULAR -> TextAttribute.WEIGHT_REGULAR;
            case SEMI_BOLD -> TextAttribute.WEIGHT_SEMIBOLD;
            case BOLD -> TextAttribute.WEIGHT_BOLD;
        };
    }

    public int cachedFontCount() {
        return cache.size();
    }
}
