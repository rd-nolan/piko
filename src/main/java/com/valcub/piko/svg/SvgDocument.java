package com.valcub.piko.svg;

/** Self-contained SVG document without an XML declaration. */
public final class SvgDocument {
    private final SvgElement root;

    public SvgDocument(int width, int height) {
        if (width <= 0 || height <= 0) throw new IllegalArgumentException("SVG dimensions must be positive");
        root = SvgElement.of("svg")
                .attr("xmlns", "http://www.w3.org/2000/svg")
                .attr("width", width).attr("height", height)
                .attr("viewBox", "0 0 " + width + " " + height)
                .attr("role", "img").attr("aria-label", "Verification image");
    }

    public SvgElement root() { return root; }

    public String render() {
        StringBuilder out = new StringBuilder(8192);
        root.writeTo(out, 0);
        return out.toString();
    }
}
