package com.valcub.piko.svg;

public final class SvgEscaper {
    private SvgEscaper() {}

    public static String escape(String value) {
        if (value == null) throw new IllegalArgumentException("SVG value cannot be null");
        StringBuilder out = new StringBuilder(value.length() + 16);
        for (int i = 0; i < value.length(); i++) {
            switch (value.charAt(i)) {
                case '&' -> out.append("&amp;");
                case '<' -> out.append("&lt;");
                case '>' -> out.append("&gt;");
                case '"' -> out.append("&quot;");
                case '\'' -> out.append("&apos;");
                default -> out.append(value.charAt(i));
            }
        }
        return out.toString();
    }
}
