package com.valcub.piko.svg;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class SvgUtil {
    private SvgUtil() {}

    public static String number(double value) {
        if (!Double.isFinite(value)) throw new IllegalArgumentException("SVG number must be finite");
        if (Math.abs(value) < 0.0005) return "0";
        return BigDecimal.valueOf(value).setScale(3, RoundingMode.HALF_UP).stripTrailingZeros().toPlainString();
    }

    public static String points(double... values) {
        if (values.length % 2 != 0) throw new IllegalArgumentException("Points require x/y pairs");
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < values.length; i += 2) {
            if (i > 0) out.append(' ');
            out.append(number(values[i])).append(',').append(number(values[i + 1]));
        }
        return out.toString();
    }
}
