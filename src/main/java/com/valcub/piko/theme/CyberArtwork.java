package com.valcub.piko.theme;

import com.valcub.piko.render.RenderContext;
import com.valcub.piko.svg.SvgBuilder;

final class CyberArtwork implements ThemeArtwork {
    @Override
    public void renderBackground(RenderContext context) {
        ThemeSvg.background(context, "#080B1A");
        for (int x = 0; x <= context.width(); x += 20) {
            context.add(SvgBuilder.line(x, 0, x, context.height()).attr("stroke", "#17324B")
                    .attr("stroke-width", "0.7").attr("opacity", "0.75"));
        }
        for (int y = 0; y <= context.height(); y += 16) {
            context.add(SvgBuilder.line(0, y, context.width(), y).attr("stroke", "#17324B")
                    .attr("stroke-width", "0.7").attr("opacity", "0.75"));
        }
        context.add(SvgBuilder.rect(5, 7, 42, 2).attr("fill", "#00F5D4").attr("opacity", "0.65"));
        context.add(SvgBuilder.rect(context.width() - 56, context.height() - 17, 45, 2).attr("fill", "#F15BB5")
                .attr("opacity", "0.68"));
        int glitches = 4 + context.options().noiseLevel().decorationCount() / 7;
        for (int i = 0; i < glitches; i++) {
            double x = context.random().nextDouble(0, context.width() - 14);
            double y = context.random().nextDouble(0, context.height());
            context.add(SvgBuilder.rect(x, y, context.random().nextDouble(3, 18), context.random().nextDouble(0.8, 2.6))
                    .attr("fill", context.random().pick(PikoTheme.CYBER.palette().accents()))
                    .attr("opacity", com.valcub.piko.svg.SvgUtil.number(context.random().nextDouble(0.28, 0.75))));
        }
    }

    @Override
    public void renderForeground(RenderContext context) {
        context.add(SvgBuilder.line(0, context.height() - 8, context.width(), context.height() - 8)
                .attr("stroke", "#00F5D4").attr("stroke-width", "1").attr("opacity", "0.75"));
    }
}
