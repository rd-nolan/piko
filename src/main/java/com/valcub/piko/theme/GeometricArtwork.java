package com.valcub.piko.theme;

import com.valcub.piko.render.RenderContext;
import com.valcub.piko.svg.SvgBuilder;
import com.valcub.piko.svg.SvgElement;

final class GeometricArtwork implements ThemeArtwork {
    @Override
    public void renderBackground(RenderContext context) {
        ThemeSvg.background(context, "#F2F4F7");
        for (int x = 0; x <= context.width(); x += 22) {
            context.add(SvgBuilder.line(x, 0, x, context.height()).attr("stroke", "#B7C0CE")
                    .attr("stroke-width", "0.6").attr("opacity", "0.34"));
        }
        for (int y = 0; y <= context.height(); y += 20) {
            context.add(SvgBuilder.line(0, y, context.width(), y).attr("stroke", "#B7C0CE")
                    .attr("stroke-width", "0.6").attr("opacity", "0.34"));
        }
        for (int i = 0; i < 5 + context.options().noiseLevel().decorationCount() / 6; i++) {
            double x = context.random().nextDouble(0, context.width());
            double y = context.random().nextDouble(0, context.height());
            double size = context.random().nextDouble(5, 17);
            String color = context.random().pick(PikoTheme.GEOMETRIC.palette().accents());
            SvgElement shape = i % 3 == 0
                    ? SvgBuilder.circle(x, y, size / 2)
                    : i % 3 == 1
                    ? SvgBuilder.rect(x, y, size, size)
                    : SvgBuilder.polygon(x, y - size / 2, x + size / 2, y + size / 2, x - size / 2, y + size / 2);
            context.add(shape.attr("fill", color).attr("opacity", "0.13")
                    .attr("stroke", color).attr("stroke-width", "0.8"));
        }
    }

    @Override
    public void renderForeground(RenderContext context) {
        context.add(SvgBuilder.polyline(-4, 63, 48, 48, 98, 66, 145, 45, 224, 60)
                .attr("fill", "none").attr("stroke", "#FA6F61").attr("stroke-width", "1.3")
                .attr("opacity", "0.65"));
    }
}
