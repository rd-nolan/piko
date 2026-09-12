package com.valcub.piko.svg;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class SvgDocumentTest {
    @Test
    void acceptsTheStandardSvgNamespace() {
        String svg = new SvgDocument(220, 80).render();

        assertTrue(svg.startsWith("<svg"));
        assertTrue(svg.contains("xmlns=\"http://www.w3.org/2000/svg\""));
        assertTrue(svg.contains("viewBox=\"0 0 220 80\""));
    }
}
