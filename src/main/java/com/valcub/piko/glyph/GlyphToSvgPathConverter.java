package com.valcub.piko.glyph;

import com.valcub.piko.svg.SvgUtil;

import java.awt.Shape;
import java.awt.font.GlyphVector;
import java.awt.geom.PathIterator;
import java.util.Objects;

/** Converts Java2D glyph outlines to compact, valid SVG path data. */
public final class GlyphToSvgPathConverter {
    public String convert(Shape shape) {
        Objects.requireNonNull(shape, "shape");
        return convert(shape.getPathIterator(null));
    }

    public String convert(GlyphVector glyphVector) {
        Objects.requireNonNull(glyphVector, "glyphVector");
        return convert(glyphVector.getOutline());
    }

    public String convert(PathIterator iterator) {
        Objects.requireNonNull(iterator, "iterator");
        StringBuilder path = new StringBuilder(512);
        double[] coordinates = new double[6];
        while (!iterator.isDone()) {
            int segment = iterator.currentSegment(coordinates);
            switch (segment) {
                case PathIterator.SEG_MOVETO -> command(path, "M", coordinates, 2);
                case PathIterator.SEG_LINETO -> command(path, "L", coordinates, 2);
                case PathIterator.SEG_QUADTO -> command(path, "Q", coordinates, 4);
                case PathIterator.SEG_CUBICTO -> command(path, "C", coordinates, 6);
                case PathIterator.SEG_CLOSE -> path.append('Z');
                default -> throw new IllegalStateException("Unsupported path segment: " + segment);
            }
            iterator.next();
        }
        return path.toString();
    }

    private static void command(StringBuilder path, String command, double[] coordinates, int count) {
        path.append(command);
        for (int i = 0; i < count; i++) {
            if (i > 0) {
                path.append(i % 2 == 0 ? ' ' : ',');
            }
            path.append(SvgUtil.number(coordinates[i]));
        }
    }
}
