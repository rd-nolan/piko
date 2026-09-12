package com.valcub.piko.theme;

import com.valcub.piko.render.RenderContext;
import com.valcub.piko.svg.SvgBuilder;
import com.valcub.piko.svg.SvgElement;
import com.valcub.piko.svg.SvgUtil;

final class StarryNightArtwork implements ThemeArtwork {
    @Override
    public void renderBackground(RenderContext context) {
        ThemeSvg.addGradient(context, "starry-sky", "#07152F", "#182E5C");
        context.add(SvgBuilder.rect(0, 0, context.width(), context.height()).attr("fill", "url(#starry-sky)"));
        if (context.options().backgroundTexture()) {
            ThemeSvg.addBlurFilter(context, "star-glow", "2.4");
        }
        int stars = 14 + context.options().noiseLevel().decorationCount();
        for (int i = 0; i < stars; i++) {
            double x = context.random().nextDouble(3, context.width() - 3);
            double y = context.random().nextDouble(3, context.height() - 3);
            double radius = context.random().nextDouble(0.45, 1.55);
            context.add(SvgBuilder.circle(x, y, radius)
                    .attr("fill", context.random().pick(PikoTheme.STARRY_NIGHT.palette().accents()))
                    .attr("opacity", SvgUtil.number(context.random().nextDouble(0.55, 1.0))));
        }
        for (int i = 0; i < 3; i++) {
            SvgElement glow = SvgBuilder.circle(context.random().nextDouble(0, context.width()),
                            context.random().nextDouble(0, context.height()), context.random().nextDouble(6, 14))
                    .attr("fill", "#6E8FD5").attr("opacity", "0.10");
            if (context.options().backgroundTexture()) {
                glow.attr("filter", "url(#star-glow)");
            }
            context.add(glow);
        }
        context.add(SvgBuilder.path("M-5 70 C32 49 64 68 101 56 S171 49 225 69 L225 80 L-5 80 Z")
                .attr("fill", "#061127").attr("opacity", "0.76"));
        context.add(SvgBuilder.path("M160 16 A12 12 0 1 0 174 33 A14 14 0 1 1 160 16 Z")
                .attr("fill", "#FBECA8").attr("opacity", "0.86"));
    }

    @Override
    public void renderForeground(RenderContext context) {
        context.add(SvgBuilder.path("M-5 58 C44 44 70 72 116 55 S179 43 225 57")
                .attr("fill", "none").attr("stroke", "#8DB6FF").attr("stroke-width", "1.2")
                .attr("opacity", "0.52"));
    }
}
