package com.valcub.piko.theme;

import com.valcub.piko.render.RenderContext;
import com.valcub.piko.svg.SvgBuilder;

final class InkArtwork implements ThemeArtwork {
    @Override
    public void renderBackground(RenderContext context) {
        ThemeSvg.background(context, "#EEE9DF");
        if (context.options().backgroundTexture()) {
            ThemeSvg.addNoiseFilter(context, "ink-paper", "0.65", "2");
            context.add(SvgBuilder.rect(0, 0, context.width(), context.height()).attr("fill", "#EEE9DF")
                    .attr("opacity", "0.20").attr("filter", "url(#ink-paper)"));
        }
        context.add(SvgBuilder.path("M0 68 L24 46 L46 60 L76 30 L111 63 L142 42 L170 62 L198 37 L220 56 L220 80 L0 80 Z")
                .attr("fill", "#252B2D").attr("opacity", "0.58"));
        context.add(SvgBuilder.path("M0 73 L34 58 L58 69 L88 49 L117 72 L146 54 L178 70 L205 52 L220 63")
                .attr("fill", "none").attr("stroke", "#161A1B").attr("stroke-width", "1.2")
                .attr("opacity", "0.62"));
        context.add(SvgBuilder.line(18, 12, 28, 71).attr("stroke", "#252B2D").attr("stroke-width", "1.1")
                .attr("opacity", "0.52"));
        context.add(SvgBuilder.line(28, 30, 9, 21).attr("stroke", "#252B2D").attr("stroke-width", "1")
                .attr("opacity", "0.52"));
        context.add(SvgBuilder.circle(187, 18, 9).attr("fill", "#8A7560").attr("opacity", "0.30"));
        context.add(SvgBuilder.circle(187, 18, 5).attr("fill", "#252B2D").attr("opacity", "0.18"));
    }

    @Override
    public void renderForeground(RenderContext context) {
        context.add(SvgBuilder.path("M-5 67 C40 45 75 75 113 56 S183 43 225 61")
                .attr("fill", "none").attr("stroke", "#252B2D").attr("stroke-width", "1.4")
                .attr("opacity", "0.55"));
    }
}
