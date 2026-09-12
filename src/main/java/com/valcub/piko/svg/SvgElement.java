package com.valcub.piko.svg;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Pattern;

/** Mutable only while building one request; serialization is allow-listed and escaped. */
public final class SvgElement {
    private static final Set<String> TAGS = Set.of(
            "svg", "g", "path", "circle", "ellipse", "rect", "line", "polyline", "polygon", "defs",
            "linearGradient", "radialGradient", "stop", "filter", "feTurbulence", "feGaussianBlur",
            "feColorMatrix", "feOffset", "feMerge", "feMergeNode", "clipPath", "mask");
    private static final Set<String> ATTRIBUTES = Set.of(
            "xmlns", "width", "height", "viewBox", "role", "aria-label", "id",
            "fill", "fill-opacity", "fill-rule", "opacity", "stroke", "stroke-width", "stroke-opacity",
            "stroke-linecap", "x", "y", "cx", "cy", "r", "rx", "ry", "x1", "x2", "y1", "y2",
            "d", "points", "offset", "stop-color", "stop-opacity", "type", "baseFrequency", "numOctaves", "seed",
            "stdDeviation", "in", "result", "filter", "clip-path", "mask");
    private static final Pattern NAME = Pattern.compile("[A-Za-z][A-Za-z0-9:.-]*");
    private final String tag;
    private final Map<String, String> attributes = new LinkedHashMap<>();
    private final List<SvgElement> children = new ArrayList<>();

    private SvgElement(String tag) {
        if (!TAGS.contains(tag)) throw new IllegalArgumentException("Unsupported SVG element: " + tag);
        this.tag = tag;
    }

    public static SvgElement of(String tag) { return new SvgElement(tag); }

    public SvgElement attr(String name, Object value) {
        Objects.requireNonNull(name, "name");
        Objects.requireNonNull(value, "value");
        if (!NAME.matcher(name).matches()) throw new IllegalArgumentException("Invalid attribute: " + name);
        if (!ATTRIBUTES.contains(name)) throw new IllegalArgumentException("Unsupported SVG attribute: " + name);
        String lowerName = name.toLowerCase(Locale.ROOT);
        if (lowerName.startsWith("on") || lowerName.equals("href") || lowerName.equals("xlink:href") || lowerName.equals("src")) {
            throw new IllegalArgumentException("Unsafe SVG attribute: " + name);
        }
        String text = String.valueOf(value);
        String compact = text.toLowerCase(Locale.ROOT).replaceAll("\\s+", "");
        boolean standardSvgNamespace = lowerName.equals("xmlns") && compact.equals("http://www.w3.org/2000/svg");
        if (lowerName.equals("xmlns") && !standardSvgNamespace) {
            throw new IllegalArgumentException("Only the standard SVG namespace is allowed");
        }
        if (!standardSvgNamespace && (compact.contains("javascript:") || compact.contains("vbscript:")
                || compact.contains("data:") || compact.contains("http://") || compact.contains("https://")
                || compact.contains("file:") || compact.contains("//"))) {
            throw new IllegalArgumentException("External or executable SVG value is not allowed");
        }
        attributes.put(name, text);
        return this;
    }

    public SvgElement add(SvgElement child) {
        children.add(Objects.requireNonNull(child, "child"));
        return this;
    }
    public List<SvgElement> children() { return List.copyOf(children); }

    void writeTo(StringBuilder out, int depth) {
        String indent = "  ".repeat(depth);
        out.append(indent).append('<').append(tag);
        attributes.forEach((name, value) -> out.append(' ').append(name).append("=\"")
                .append(SvgEscaper.escape(value)).append('"'));
        if (children.isEmpty()) { out.append("/>\n"); return; }
        out.append(">\n");
        children.forEach(child -> child.writeTo(out, depth + 1));
        out.append(indent).append("</").append(tag).append(">\n");
    }
}
