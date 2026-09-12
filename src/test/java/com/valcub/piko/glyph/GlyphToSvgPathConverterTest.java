package com.valcub.piko.glyph;

import com.valcub.piko.font.BuiltinFonts;
import com.valcub.piko.font.FontRegistry;
import org.junit.jupiter.api.Test;

import java.awt.Font;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GlyphToSvgPathConverterTest {
    @Test
    void convertsGlyphOutlinesWithoutSvgTextOrNonFiniteNumbers() {
        Font font = FontRegistry.defaultRegistry().derive(BuiltinFonts.LORA_REGULAR, 48);
        FontRenderContext context = new FontRenderContext(new AffineTransform(), true, true);
        String path = new GlyphToSvgPathConverter().convert(font.createGlyphVector(context, "A7m9").getOutline());

        assertFalse(path.isBlank());
        assertTrue(path.matches("[MLQCZ0-9., -]+"));
        assertFalse(path.contains("NaN"));
        assertFalse(path.contains("Infinity"));
    }
}
