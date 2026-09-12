package com.valcub.piko.render;

import com.valcub.piko.font.FontRegistry;
import com.valcub.piko.glyph.GlyphToSvgPathConverter;
import com.valcub.piko.svg.SvgBuilder;
import com.valcub.piko.svg.SvgElement;
import com.valcub.piko.svg.SvgUtil;

import java.awt.Font;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.util.Objects;

/** Converts one Java2D glyph outline to a transformed SVG path. */
public final class CharacterRenderer {
    private final GlyphToSvgPathConverter converter;

    public CharacterRenderer() {
        this(new GlyphToSvgPathConverter());
    }

    public CharacterRenderer(GlyphToSvgPathConverter converter) {
        this.converter = Objects.requireNonNull(converter, "converter");
    }

    public SvgElement render(char value, CharacterStyle style, FontRegistry registry) {
        Objects.requireNonNull(style, "style");
        Objects.requireNonNull(registry, "registry");
        Font font = registry.derive(style.font(), style.fontSize());
        FontRenderContext fontRenderContext = new FontRenderContext(new AffineTransform(), true, true);
        GlyphVector glyphVector = font.createGlyphVector(fontRenderContext, String.valueOf(value));
        Shape outline = glyphVector.getGlyphOutline(0);
        Rectangle2D bounds = outline.getBounds2D();
        if (bounds.isEmpty()) {
            throw new IllegalArgumentException("Font has no outline for character: " + value);
        }
        AffineTransform transform = new AffineTransform();
        transform.translate(style.centerX(), style.centerY());
        transform.rotate(Math.toRadians(style.rotationDegrees()));
        transform.scale(style.scaleX(), style.scaleY());
        transform.translate(-bounds.getCenterX(), -bounds.getCenterY());
        Shape positioned = transform.createTransformedShape(outline);
        String path = converter.convert(positioned);
        if (path.isBlank()) {
            throw new IllegalArgumentException("Glyph outline converted to an empty path");
        }
        return SvgBuilder.path(path)
                .attr("fill", style.fill())
                .attr("fill-rule", "nonzero")
                .attr("opacity", SvgUtil.number(style.opacity()));
    }
}
