package com.valcub.piko.glyph;

import com.valcub.piko.font.FontRegistry;
import com.valcub.piko.render.CharacterRenderer;
import com.valcub.piko.render.CharacterStyle;
import com.valcub.piko.svg.SvgElement;

import java.util.Objects;

/** Compatibility facade for callers that organize glyph work under the glyph package. */
public final class GlyphRenderer {
    private final CharacterRenderer delegate;

    public GlyphRenderer() {
        this.delegate = new CharacterRenderer();
    }

    public GlyphRenderer(CharacterRenderer delegate) {
        this.delegate = Objects.requireNonNull(delegate, "delegate");
    }

    public SvgElement render(char value, CharacterStyle style, FontRegistry registry) {
        return delegate.render(value, style, registry);
    }
}
