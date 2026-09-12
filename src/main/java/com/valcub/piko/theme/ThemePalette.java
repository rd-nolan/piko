package com.valcub.piko.theme;

import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

/** Immutable palette shared by one theme. */
public record ThemePalette(List<String> characterColors, List<String> accentColors,
                           List<String> backgroundColors, List<String> noiseColors) {
    private static final Pattern COLOR = Pattern.compile("#[0-9A-Fa-f]{6}([0-9A-Fa-f]{2})?");

    public ThemePalette {
        characterColors = copyColors(characterColors, "characterColors");
        accentColors = copyColors(accentColors, "accentColors");
        backgroundColors = copyColors(backgroundColors, "backgroundColors");
        noiseColors = copyColors(noiseColors, "noiseColors");
    }

    /** Convenience constructor for callers that do not need a separate noise palette. */
    public ThemePalette(List<String> backgrounds, List<String> accents, List<String> foregrounds) {
        this(foregrounds, accents, backgrounds, accents);
    }

    public List<String> backgroundColors() {
        return backgroundColors;
    }

    public List<String> accentColors() {
        return accentColors;
    }

    public List<String> textColors() {
        return characterColors;
    }

    public List<String> backgrounds() {
        return backgroundColors;
    }

    public List<String> accents() {
        return accentColors;
    }

    public List<String> foregrounds() {
        return characterColors;
    }

    private static List<String> copyColors(List<String> values, String name) {
        Objects.requireNonNull(values, name);
        if (values.isEmpty()) {
            throw new IllegalArgumentException(name + " cannot be empty");
        }
        List<String> copy = List.copyOf(values);
        copy.forEach(value -> {
            if (value == null || !COLOR.matcher(value).matches()) {
                throw new IllegalArgumentException(name + " must contain hexadecimal colors");
            }
        });
        return copy;
    }
}
