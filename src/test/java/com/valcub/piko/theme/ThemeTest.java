package com.valcub.piko.theme;

import com.valcub.piko.captcha.PikoCaptcha;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ThemeTest {
    private static final Map<PikoTheme, String> MARKERS = Map.of(
            PikoTheme.WATERCOLOR, "#FBF6E9",
            PikoTheme.STARRY_NIGHT, "starry-sky",
            PikoTheme.GLASS, "glass-sky",
            PikoTheme.BOTANICAL, "#52724C",
            PikoTheme.GEOMETRIC, "#B7C0CE",
            PikoTheme.CUTE, "#FF9FB2",
            PikoTheme.INK, "#252B2D",
            PikoTheme.CYBER, "#17324B");

    @Test
    void everyBuiltInThemeRendersItsOwnArtwork() {
        for (PikoTheme theme : PikoTheme.values()) {
            String svg = PikoCaptcha.builder().theme(theme).seed(theme.ordinal() + 100).build().generate().svg();
            assertTrue(svg.contains(MARKERS.get(theme)), theme.id());
            assertFalse(svg.contains("<text"), theme.id());
            assertFalse(svg.contains("<![CDATA"), theme.id());
        }
    }
}
