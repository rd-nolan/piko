package com.valcub.piko.captcha;

import com.valcub.piko.font.FontRegistry;
import com.valcub.piko.font.FontSelectionStrategy;
import com.valcub.piko.theme.CaptchaTheme;

import java.util.Objects;

/** Small thread-safe facade for generating SVG character captchas. */
public final class PikoCaptcha {
    private final CaptchaOptions options;
    private final CaptchaGenerator generator;

    public PikoCaptcha(CaptchaOptions options) {
        this.options = Objects.requireNonNull(options, "options");
        this.generator = new CaptchaGenerator();
    }

    public static Builder builder() {
        return new Builder();
    }

    public CaptchaResult generate() {
        return generator.generate(options, options.newRandomProvider());
    }

    public CaptchaOptions options() {
        return options;
    }

    public static final class Builder {
        private final CaptchaOptions.Builder delegate = CaptchaOptions.builder();

        public Builder width(int width) {
            delegate.width(width);
            return this;
        }

        public Builder height(int height) {
            delegate.height(height);
            return this;
        }

        public Builder length(int length) {
            delegate.length(length);
            return this;
        }

        public Builder theme(CaptchaTheme theme) {
            delegate.theme(theme);
            return this;
        }

        public Builder characters(String characters) {
            delegate.characters(characters);
            return this;
        }

        public Builder alphabet(String alphabet) {
            delegate.alphabet(alphabet);
            return this;
        }

        public Builder caseSensitive(boolean caseSensitive) {
            delegate.caseSensitive(caseSensitive);
            return this;
        }

        public Builder noiseLevel(NoiseLevel level) {
            delegate.noiseLevel(level);
            return this;
        }

        public Builder backgroundTexture(boolean enabled) {
            delegate.backgroundTexture(enabled);
            return this;
        }

        public Builder foregroundCurve(boolean enabled) {
            delegate.foregroundCurve(enabled);
            return this;
        }

        public Builder fontSelectionStrategy(FontSelectionStrategy strategy) {
            delegate.fontSelectionStrategy(strategy);
            return this;
        }

        public Builder seed(long seed) {
            delegate.seed(seed);
            return this;
        }

        public Builder noSeed() {
            delegate.noSeed();
            return this;
        }

        public Builder fontRegistry(FontRegistry registry) {
            delegate.fontRegistry(registry);
            return this;
        }

        public PikoCaptcha build() {
            return new PikoCaptcha(delegate.build());
        }
    }
}
