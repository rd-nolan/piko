package com.valcub.piko.svg;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SvgEscaperTest {
    @Test
    void escapesMarkupAndQuotes() {
        assertEquals("&lt;&amp;&gt;&quot;&apos;", SvgEscaper.escape("<&>\"'"));
    }

    @Test
    void rejectsExecutableAndExternalValues() {
        assertThrows(IllegalArgumentException.class, () -> SvgElement.of("rect").attr("onclick", "alert(1)"));
        assertThrows(IllegalArgumentException.class, () -> SvgElement.of("rect").attr("fill", "url(https://example.test/x)"));
        assertThrows(IllegalArgumentException.class, () -> SvgElement.of("rect").attr("fill", "javascript:alert(1)"));
        assertThrows(IllegalArgumentException.class, () -> SvgElement.of("rect").attr("fill", "data:image/svg+xml;base64,AAAA"));
        assertThrows(IllegalArgumentException.class, () -> SvgElement.of("rect").attr("filter", "url(//attacker.example/filter)"));
    }

    @Test
    void rejectsAttributesOutsideTheSafeAllowlist() {
        assertThrows(IllegalArgumentException.class,
                () -> SvgElement.of("rect").attr("style", "background-image:url(data:image/svg+xml;base64,AAAA)"));
        assertThrows(IllegalArgumentException.class,
                () -> SvgElement.of("g").attr("xml:base", "//attacker.example/"));
        assertThrows(IllegalArgumentException.class,
                () -> SvgElement.of("rect").attr("data-custom", "unexpected"));
    }
}
