package com.valcub.piko.theme;

import com.valcub.piko.render.RenderContext;
import com.valcub.piko.svg.SvgBuilder;
import com.valcub.piko.svg.SvgElement;
import com.valcub.piko.svg.SvgUtil;

final class WatercolorArtwork implements ThemeArtwork {
    @Override
    public void renderBackground(RenderContext context) {
        ThemeSvg.background(context, "#FBF6E9");
        if (context.options().backgroundTexture()) {
            ThemeSvg.addNoiseFilter(context, "watercolor-noise", "0.8", "1");
        }
        int count = 3 + context.options().noiseLevel().decorationCount() / 8;
        for (int i = 0; i < count; i++) {
            double x = context.random().nextDouble(-18, context.width() + 18);
            double y = context.random().nextDouble(-12, context.height() + 12);
            double rx = context.random().nextDouble(18, 48);
            double ry = context.random().nextDouble(9, 25);
            SvgElement wash = SvgBuilder.ellipse(x, y, rx, ry)
                    .attr("fill", context.random().pick(PikoTheme.WATERCOLOR.palette().accents()))
                    .attr("opacity", SvgUtil.number(context.random().nextDouble(0.10, 0.24)));
            if (context.options().backgroundTexture()) {
                wash.attr("filter", "url(#watercolor-noise)");
            }
            context.add(wash);
        }
        context.add(SvgBuilder.path("M0 56 C38 29 65 78 111 52 S180 27 220 48 L220 80 L0 80 Z")
                .attr("fill", "#DCE9DF").attr("opacity", "0.26"));
        context.add(SvgBuilder.path("M-5 20 C44 33 75 9 117 25 S181 33 225 16")
                .attr("fill", "none").attr("stroke", "#D29E9B").attr("stroke-width", "1.4")
                .attr("opacity", "0.36"));
    }

    @Override
    public void renderForeground(RenderContext context) {
        context.add(SvgBuilder.path("M-5 66 C38 45 70 77 112 57 S181 39 225 59")
                .attr("fill", "none").attr("stroke", "#9E6B75").attr("stroke-width", "1.5")
                .attr("opacity", "0.42"));
    }
}
