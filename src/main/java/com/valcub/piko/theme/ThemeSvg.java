package com.valcub.piko.theme;

import com.valcub.piko.render.RenderContext;
import com.valcub.piko.svg.SvgBuilder;
import com.valcub.piko.svg.SvgElement;

final class ThemeSvg {
    private ThemeSvg() {
    }

    static void background(RenderContext context, String color) {
        context.add(SvgBuilder.rect(0, 0, context.width(), context.height()).attr("fill", color));
    }

    static void addGradient(RenderContext context, String id, String start, String end) {
        SvgElement defs = SvgBuilder.element("defs");
        SvgElement gradient = SvgBuilder.element("linearGradient").attr("id", id).attr("x1", "0%")
                .attr("y1", "0%").attr("x2", "100%").attr("y2", "100%");
        gradient.add(SvgBuilder.element("stop").attr("offset", "0%").attr("stop-color", start));
        gradient.add(SvgBuilder.element("stop").attr("offset", "100%").attr("stop-color", end));
        defs.add(gradient);
        context.add(defs);
    }

    static void addNoiseFilter(RenderContext context, String id, String frequency, String octaves) {
        SvgElement defs = SvgBuilder.element("defs");
        SvgElement filter = SvgBuilder.element("filter").attr("id", id).attr("x", "-10%").attr("y", "-10%")
                .attr("width", "120%").attr("height", "120%");
        filter.add(SvgBuilder.element("feTurbulence").attr("type", "fractalNoise")
                .attr("baseFrequency", frequency).attr("numOctaves", octaves).attr("seed", "17"));
        defs.add(filter);
        context.add(defs);
    }

    static void addBlurFilter(RenderContext context, String id, String deviation) {
        SvgElement defs = SvgBuilder.element("defs");
        SvgElement filter = SvgBuilder.element("filter").attr("id", id).attr("x", "-30%").attr("y", "-30%")
                .attr("width", "160%").attr("height", "160%");
        filter.add(SvgBuilder.element("feGaussianBlur").attr("stdDeviation", deviation));
        defs.add(filter);
        context.add(defs);
    }
}
