package com.valcub.piko.theme;

import com.valcub.piko.render.RenderContext;
import com.valcub.piko.svg.SvgBuilder;
import com.valcub.piko.svg.SvgElement;

final class GlassArtwork implements ThemeArtwork {
    @Override
    public void renderBackground(RenderContext context) {
        ThemeSvg.addGradient(context, "glass-sky", "#CDE2FF", "#F9D9EE");
        context.add(SvgBuilder.rect(0, 0, context.width(), context.height()).attr("fill", "url(#glass-sky)"));
        if (context.options().backgroundTexture()) {
            ThemeSvg.addBlurFilter(context, "glass-blur", "3");
        }
        int bubbles = 4 + context.options().noiseLevel().decorationCount() / 4;
        for (int i = 0; i < bubbles; i++) {
            double x = context.random().nextDouble(-4, context.width() + 4);
            double y = context.random().nextDouble(-5, context.height() + 5);
            double radius = context.random().nextDouble(5, 17);
            SvgElement bubble = SvgBuilder.circle(x, y, radius).attr("fill", "#FFFFFF")
                    .attr("fill-opacity", "0.14").attr("stroke", "#FFFFFF").attr("stroke-width", "1")
                    .attr("stroke-opacity", "0.58");
            if (context.options().backgroundTexture()) {
                bubble.attr("filter", "url(#glass-blur)");
            }
            context.add(bubble);
        }
        context.add(SvgBuilder.ellipse(42, 17, 35, 10).attr("fill", "#FFFFFF").attr("opacity", "0.24"));
        context.add(SvgBuilder.ellipse(180, 67, 46, 12).attr("fill", "#FFFFFF").attr("opacity", "0.18"));
    }

    @Override
    public void renderForeground(RenderContext context) {
        context.add(SvgBuilder.path("M-5 63 C46 41 75 77 119 56 S181 40 225 61")
                .attr("fill", "none").attr("stroke", "#FFFFFF").attr("stroke-width", "1.6")
                .attr("opacity", "0.58"));
    }
}
