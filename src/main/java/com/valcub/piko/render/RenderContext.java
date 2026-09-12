package com.valcub.piko.render;

import com.valcub.piko.captcha.CaptchaOptions;
import com.valcub.piko.font.FontDescriptor;
import com.valcub.piko.layout.CharacterLayout;
import com.valcub.piko.random.RandomProvider;
import com.valcub.piko.svg.SvgDocument;
import com.valcub.piko.svg.SvgElement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/** Request-local rendering state passed to themes. */
public final class RenderContext {
    private final SvgDocument document;
    private final CaptchaOptions options;
    private final String code;
    private final RandomProvider random;
    private final List<CharacterLayout> layouts;
    private final Map<String, FontDescriptor> selectedFonts = new HashMap<>();

    public RenderContext(SvgDocument document, CaptchaOptions options, String code,
                         RandomProvider random, List<CharacterLayout> layouts) {
        this.document = Objects.requireNonNull(document, "document");
        this.options = Objects.requireNonNull(options, "options");
        this.code = Objects.requireNonNull(code, "code");
        this.random = Objects.requireNonNull(random, "random");
        this.layouts = List.copyOf(Objects.requireNonNull(layouts, "layouts"));
        if (this.layouts.size() != code.length()) {
            throw new IllegalArgumentException("one layout is required per code character");
        }
    }

    public SvgDocument document() {
        return document;
    }

    public SvgElement root() {
        return document.root();
    }

    public CaptchaOptions options() {
        return options;
    }

    public String code() {
        return code;
    }

    public int width() {
        return options.width();
    }

    public int height() {
        return options.height();
    }

    public RandomProvider random() {
        return random;
    }

    public List<CharacterLayout> layouts() {
        return layouts;
    }

    public CharacterLayout layout(int index) {
        return layouts.get(index);
    }

    public FontDescriptor chooseFont(String key, List<FontDescriptor> pool) {
        Objects.requireNonNull(key, "key");
        Objects.requireNonNull(pool, "pool");
        if (pool.isEmpty()) {
            throw new IllegalArgumentException("theme font pool cannot be empty");
        }
        if (options.fontSelectionStrategy() == com.valcub.piko.font.FontSelectionStrategy.SINGLE_FONT) {
            return selectedFonts.computeIfAbsent(key, ignored -> random.pick(pool));
        }
        return random.pick(pool);
    }

    public void add(SvgElement element) {
        root().add(Objects.requireNonNull(element, "element"));
    }
}
