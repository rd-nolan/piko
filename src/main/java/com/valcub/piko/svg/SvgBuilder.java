package com.valcub.piko.svg;

import java.util.Objects;

/** Concise factories for the supported SVG subset. */
public final class SvgBuilder {
    private SvgBuilder() {
    }

    public static SvgElement element(String name) {
        return SvgElement.of(name);
    }

    public static SvgElement group() {
        return element("g");
    }

    public static SvgElement path(String data) {
        return element("path").attr("d", data);
    }

    public static SvgElement path(SvgPath path) {
        return path(Objects.requireNonNull(path, "path").data());
    }

    public static SvgElement rect(double x, double y, double width, double height) {
        return element("rect").attr("x", SvgUtil.number(x)).attr("y", SvgUtil.number(y))
                .attr("width", SvgUtil.number(width)).attr("height", SvgUtil.number(height));
    }

    public static SvgElement circle(double cx, double cy, double radius) {
        return element("circle").attr("cx", SvgUtil.number(cx)).attr("cy", SvgUtil.number(cy))
                .attr("r", SvgUtil.number(radius));
    }

    public static SvgElement ellipse(double cx, double cy, double rx, double ry) {
        return element("ellipse").attr("cx", SvgUtil.number(cx)).attr("cy", SvgUtil.number(cy))
                .attr("rx", SvgUtil.number(rx)).attr("ry", SvgUtil.number(ry));
    }

    public static SvgElement line(double x1, double y1, double x2, double y2) {
        return element("line").attr("x1", SvgUtil.number(x1)).attr("y1", SvgUtil.number(y1))
                .attr("x2", SvgUtil.number(x2)).attr("y2", SvgUtil.number(y2));
    }

    public static SvgElement polyline(double... points) {
        return element("polyline").attr("points", SvgUtil.points(points));
    }

    public static SvgElement polygon(double... points) {
        return element("polygon").attr("points", SvgUtil.points(points));
    }
}
