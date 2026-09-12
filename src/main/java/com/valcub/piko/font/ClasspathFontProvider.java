package com.valcub.piko.font;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

/** Loads bundled fonts only; it never consults system fonts or the network. */
public final class ClasspathFontProvider implements FontProvider {
    private final ClassLoader classLoader;

    public ClasspathFontProvider() {
        this(ClasspathFontProvider.class.getClassLoader());
    }

    public ClasspathFontProvider(ClassLoader classLoader) {
        this.classLoader = Objects.requireNonNull(classLoader, "classLoader");
    }

    @Override
    public FontResource load(FontDescriptor descriptor) throws IOException {
        try (InputStream input = classLoader.getResourceAsStream(descriptor.resourcePath())) {
            if (input == null) {
                throw new IOException("Bundled font not found: " + descriptor.resourcePath());
            }
            try {
                return new FontResource(descriptor, Font.createFont(Font.TRUETYPE_FONT, input));
            } catch (FontFormatException exception) {
                throw new IOException("Invalid bundled font: " + descriptor.resourcePath(), exception);
            }
        }
    }
}
