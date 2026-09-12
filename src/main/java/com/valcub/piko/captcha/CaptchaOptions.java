package com.valcub.piko.captcha;

import com.valcub.piko.font.FontRegistry;
import com.valcub.piko.font.FontSelectionStrategy;
import com.valcub.piko.random.RandomProvider;
import com.valcub.piko.random.SecureRandomProvider;
import com.valcub.piko.random.SeededRandomProvider;
import com.valcub.piko.theme.CaptchaTheme;
import com.valcub.piko.theme.PikoTheme;

import java.util.Locale;
import java.util.Objects;

/** Immutable, validated options for one captcha generator. */
public final class CaptchaOptions {
    public static final int DEFAULT_WIDTH = 220;
    public static final int DEFAULT_HEIGHT = 80;
    public static final int DEFAULT_LENGTH = 4;
    public static final String DEFAULT_CHARACTERS = "ABCDEFGHJKLMNPQRSTUVWXYZ23456789";

    private final int width;
    private final int height;
    private final int length;
    private final CaptchaTheme theme;
    private final String characters;
    private final boolean caseSensitive;
    private final NoiseLevel noiseLevel;
    private final boolean backgroundTexture;
    private final boolean foregroundCurve;
    private final FontSelectionStrategy fontSelectionStrategy;
    private final Long seed;
    private final FontRegistry fontRegistry;

    private CaptchaOptions(Builder builder) {
        if (builder.width < 40 || builder.width > 2000) {
            throw new IllegalArgumentException("width must be between 40 and 2000");
        }
        if (builder.height < 30 || builder.height > 1000) {
            throw new IllegalArgumentException("height must be between 30 and 1000");
        }
        if (builder.length < 1 || builder.length > 12) {
            throw new IllegalArgumentException("length must be between 1 and 12");
        }
        this.width = builder.width;
        this.height = builder.height;
        this.length = builder.length;
        this.theme = Objects.requireNonNull(builder.theme, "theme");
        this.characters = normalizeCharacters(builder.characters, builder.caseSensitive);
        this.caseSensitive = builder.caseSensitive;
        this.noiseLevel = Objects.requireNonNull(builder.noiseLevel, "noiseLevel");
        this.backgroundTexture = builder.backgroundTexture;
        this.foregroundCurve = builder.foregroundCurve;
        this.fontSelectionStrategy = Objects.requireNonNull(builder.fontSelectionStrategy, "fontSelectionStrategy");
        this.seed = builder.seed;
        this.fontRegistry = Objects.requireNonNull(builder.fontRegistry, "fontRegistry");
    }

    public static Builder builder() {
        return new Builder();
    }

    public int width() {
        return width;
    }

    public int height() {
        return height;
    }

    public int length() {
        return length;
    }

    public CaptchaTheme theme() {
        return theme;
    }

    public String characters() {
        return characters;
    }

    public String alphabet() {
        return characters;
    }

    public boolean caseSensitive() {
        return caseSensitive;
    }

    public NoiseLevel noiseLevel() {
        return noiseLevel;
    }

    public boolean backgroundTexture() {
        return backgroundTexture;
    }

    public boolean foregroundCurve() {
        return foregroundCurve;
    }

    public FontSelectionStrategy fontSelectionStrategy() {
        return fontSelectionStrategy;
    }

    public Long seed() {
        return seed;
    }

    public FontRegistry fontRegistry() {
        return fontRegistry;
    }

    public RandomProvider newRandomProvider() {
        return seed == null ? new SecureRandomProvider() : new SeededRandomProvider(seed);
    }

    private static String normalizeCharacters(String raw, boolean caseSensitive) {
        Objects.requireNonNull(raw, "characters");
        String source = caseSensitive ? raw : raw.toUpperCase(Locale.ROOT);
        StringBuilder unique = new StringBuilder(source.length());
        for (int i = 0; i < source.length(); i++) {
            char value = source.charAt(i);
            if (Character.isISOControl(value) || Character.isWhitespace(value)) {
                throw new IllegalArgumentException("characters cannot contain whitespace or control characters");
            }
            if (unique.indexOf(String.valueOf(value)) < 0) {
                unique.append(value);
            }
        }
        if (unique.length() < 2) {
            throw new IllegalArgumentException("characters must contain at least two unique values");
        }
        return unique.toString();
    }

    public static final class Builder {
        private int width = DEFAULT_WIDTH;
        private int height = DEFAULT_HEIGHT;
        private int length = DEFAULT_LENGTH;
        private CaptchaTheme theme = PikoTheme.WATERCOLOR;
        private String characters = DEFAULT_CHARACTERS;
        private boolean caseSensitive;
        private NoiseLevel noiseLevel = NoiseLevel.NORMAL;
        private boolean backgroundTexture = true;
        private boolean foregroundCurve = true;
        private FontSelectionStrategy fontSelectionStrategy = FontSelectionStrategy.RANDOM_PER_CHARACTER;
        private Long seed;
        private FontRegistry fontRegistry = FontRegistry.defaultRegistry();

        public Builder width(int width) {
            this.width = width;
            return this;
        }

        public Builder height(int height) {
            this.height = height;
            return this;
        }

        public Builder length(int length) {
            this.length = length;
            return this;
        }

        public Builder theme(CaptchaTheme theme) {
            this.theme = theme;
            return this;
        }

        public Builder characters(String characters) {
            this.characters = characters;
            return this;
        }

        public Builder alphabet(String alphabet) {
            return characters(alphabet);
        }

        public Builder caseSensitive(boolean caseSensitive) {
            this.caseSensitive = caseSensitive;
            return this;
        }

        public Builder noiseLevel(NoiseLevel noiseLevel) {
            this.noiseLevel = noiseLevel;
            return this;
        }

        public Builder backgroundTexture(boolean backgroundTexture) {
            this.backgroundTexture = backgroundTexture;
            return this;
        }

        public Builder foregroundCurve(boolean foregroundCurve) {
            this.foregroundCurve = foregroundCurve;
            return this;
        }

        public Builder fontSelectionStrategy(FontSelectionStrategy strategy) {
            this.fontSelectionStrategy = strategy;
            return this;
        }

        public Builder seed(long seed) {
            this.seed = seed;
            return this;
        }

        public Builder noSeed() {
            this.seed = null;
            return this;
        }

        public Builder fontRegistry(FontRegistry fontRegistry) {
            this.fontRegistry = fontRegistry;
            return this;
        }

        public CaptchaOptions build() {
            return new CaptchaOptions(this);
        }
    }
}
