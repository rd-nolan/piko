package com.valcub.piko.theme;

import com.valcub.piko.render.RenderContext;
import com.valcub.piko.svg.SvgBuilder;

final class BotanicalArtwork implements ThemeArtwork {
    @Override
    public void renderBackground(RenderContext context) {
        ThemeSvg.background(context, "#F3F0DE");
        context.add(SvgBuilder.path("M9 80 C17 62 18 39 26 13").attr("fill", "none")
                .attr("stroke", "#52724C").attr("stroke-width", "1.4").attr("opacity", "0.72"));
        context.add(SvgBuilder.path("M25 38 C12 31 9 23 14 17 C24 20 29 27 25 38 Z")
                .attr("fill", "#91B276").attr("opacity", "0.76"));
        context.add(SvgBuilder.path("M20 54 C31 45 38 44 42 48 C38 58 30 61 20 54 Z")
                .attr("fill", "#729A68").attr("opacity", "0.73"));
        context.add(SvgBuilder.path("M204 80 C197 59 201 35 194 12").attr("fill", "none")
                .attr("stroke", "#52724C").attr("stroke-width", "1.4").attr("opacity", "0.72"));
        context.add(SvgBuilder.path("M196 36 C207 27 215 26 219 30 C216 40 208 43 196 36 Z")
                .attr("fill", "#A3BE7D").attr("opacity", "0.78"));
        context.add(SvgBuilder.path("M199 54 C188 47 182 48 179 53 C185 61 192 62 199 54 Z")
                .attr("fill", "#719564").attr("opacity", "0.72"));
        if (context.options().backgroundTexture()) {
            for (int i = 0; i < 6 + context.options().noiseLevel().decorationCount() / 5; i++) {
                context.add(SvgBuilder.circle(context.random().nextDouble(0, context.width()),
                                context.random().nextDouble(0, context.height()), context.random().nextDouble(0.4, 1.2))
                        .attr("fill", "#6F8062").attr("opacity", "0.20"));
            }
        }
    }

    @Override
    public void renderForeground(RenderContext context) {
        context.add(SvgBuilder.path("M-4 70 C48 47 69 74 111 58 S180 47 224 64")
                .attr("fill", "none").attr("stroke", "#63845A").attr("stroke-width", "1.3")
                .attr("opacity", "0.62"));
    }
}
