package com.valcub.piko.theme;

import com.valcub.piko.render.RenderContext;
import com.valcub.piko.svg.SvgBuilder;

final class CuteArtwork implements ThemeArtwork {
    @Override
    public void renderBackground(RenderContext context) {
        ThemeSvg.background(context, "#FFF3E8");
        context.add(SvgBuilder.circle(25, 19, 10).attr("fill", "#FFD166").attr("opacity", "0.35"));
        context.add(SvgBuilder.path("M12 23 L14 9 L22 16 L30 9 L34 24 Z").attr("fill", "#FF9FB2")
                .attr("opacity", "0.68"));
        context.add(SvgBuilder.circle(20, 20, 1.2).attr("fill", "#603C57"));
        context.add(SvgBuilder.circle(28, 20, 1.2).attr("fill", "#603C57"));
        context.add(SvgBuilder.path("M23 24 Q25 26 27 24").attr("fill", "none").attr("stroke", "#603C57")
                .attr("stroke-width", "1"));
        context.add(SvgBuilder.circle(context.width() - 26, context.height() - 18, 10).attr("fill", "#9AD7D1")
                .attr("opacity", "0.46"));
        context.add(SvgBuilder.path("M184 67 C190 54 210 54 215 67 C207 74 193 74 184 67 Z")
                .attr("fill", "#B8A1E5").attr("opacity", "0.55"));
        context.add(SvgBuilder.path("M190 61 C196 54 204 54 210 61").attr("fill", "none")
                .attr("stroke", "#704E86").attr("stroke-width", "1.2"));
        int dots = 5 + context.options().noiseLevel().decorationCount() / 8;
        for (int i = 0; i < dots; i++) {
            context.add(SvgBuilder.circle(context.random().nextDouble(40, context.width() - 40),
                            context.random().nextDouble(8, context.height() - 8), context.random().nextDouble(0.8, 2.2))
                    .attr("fill", context.random().pick(PikoTheme.CUTE.palette().accents())).attr("opacity", "0.48"));
        }
    }

    @Override
    public void renderForeground(RenderContext context) {
        context.add(SvgBuilder.path("M-3 65 C42 47 73 76 111 57 S178 43 223 61")
                .attr("fill", "none").attr("stroke", "#FF9FB2").attr("stroke-width", "2")
                .attr("stroke-linecap", "round").attr("opacity", "0.66"));
    }
}
