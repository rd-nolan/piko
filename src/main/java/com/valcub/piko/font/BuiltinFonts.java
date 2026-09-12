package com.valcub.piko.font;

import java.util.List;

/** Descriptors for the curated, bundled Google Fonts subset. */
public final class BuiltinFonts {
    public static final FontDescriptor LORA_REGULAR = font("Lora", FontWeight.REGULAR, "Lora[wght].ttf");
    public static final FontDescriptor LORA_SEMI_BOLD = font("Lora", FontWeight.SEMI_BOLD, "Lora[wght].ttf");
    public static final FontDescriptor LORA_BOLD = font("Lora", FontWeight.BOLD, "Lora[wght].ttf");
    public static final FontDescriptor MERRIWEATHER_REGULAR = font("Merriweather", FontWeight.REGULAR, "Merriweather[opsz,wdth,wght].ttf");
    public static final FontDescriptor MERRIWEATHER_BOLD = font("Merriweather", FontWeight.BOLD, "Merriweather[opsz,wdth,wght].ttf");
    public static final FontDescriptor LIBRE_BASKERVILLE_REGULAR = font("Libre Baskerville", FontWeight.REGULAR, "LibreBaskerville[wght].ttf");
    public static final FontDescriptor LIBRE_BASKERVILLE_BOLD = font("Libre Baskerville", FontWeight.BOLD, "LibreBaskerville[wght].ttf");
    public static final FontDescriptor PLAYFAIR_DISPLAY_REGULAR = font("Playfair Display", FontWeight.REGULAR, "PlayfairDisplay[wght].ttf");
    public static final FontDescriptor PLAYFAIR_DISPLAY_SEMI_BOLD = font("Playfair Display", FontWeight.SEMI_BOLD, "PlayfairDisplay[wght].ttf");
    public static final FontDescriptor PLAYFAIR_DISPLAY_BOLD = font("Playfair Display", FontWeight.BOLD, "PlayfairDisplay[wght].ttf");
    public static final FontDescriptor ROBOTO_SLAB_REGULAR = font("Roboto Slab", FontWeight.REGULAR, "RobotoSlab[wght].ttf");
    public static final FontDescriptor ROBOTO_SLAB_SEMI_BOLD = font("Roboto Slab", FontWeight.SEMI_BOLD, "RobotoSlab[wght].ttf");
    public static final FontDescriptor ROBOTO_SLAB_BOLD = font("Roboto Slab", FontWeight.BOLD, "RobotoSlab[wght].ttf");
    public static final FontDescriptor FREDOKA_REGULAR = font("Fredoka", FontWeight.REGULAR, "Fredoka[wdth,wght].ttf");
    public static final FontDescriptor FREDOKA_SEMI_BOLD = font("Fredoka", FontWeight.SEMI_BOLD, "Fredoka[wdth,wght].ttf");
    public static final FontDescriptor SPACE_GROTESK_REGULAR = font("Space Grotesk", FontWeight.REGULAR, "SpaceGrotesk[wght].ttf");
    public static final FontDescriptor SPACE_GROTESK_SEMI_BOLD = font("Space Grotesk", FontWeight.SEMI_BOLD, "SpaceGrotesk[wght].ttf");
    public static final FontDescriptor OXANIUM_REGULAR = font("Oxanium", FontWeight.REGULAR, "Oxanium[wght].ttf");
    public static final FontDescriptor OXANIUM_SEMI_BOLD = font("Oxanium", FontWeight.SEMI_BOLD, "Oxanium[wght].ttf");
    public static final FontDescriptor RAJDHANI_REGULAR = font("Rajdhani", FontWeight.REGULAR, "Rajdhani-SemiBold.ttf");
    public static final FontDescriptor RAJDHANI_SEMI_BOLD = font("Rajdhani", FontWeight.SEMI_BOLD, "Rajdhani-SemiBold.ttf");

    public static final List<FontDescriptor> ALL = List.of(
            LORA_REGULAR, LORA_SEMI_BOLD, LORA_BOLD, MERRIWEATHER_REGULAR, MERRIWEATHER_BOLD,
            LIBRE_BASKERVILLE_REGULAR, LIBRE_BASKERVILLE_BOLD, PLAYFAIR_DISPLAY_REGULAR,
            PLAYFAIR_DISPLAY_SEMI_BOLD, PLAYFAIR_DISPLAY_BOLD, ROBOTO_SLAB_REGULAR,
            ROBOTO_SLAB_SEMI_BOLD, ROBOTO_SLAB_BOLD, FREDOKA_REGULAR, FREDOKA_SEMI_BOLD,
            SPACE_GROTESK_REGULAR, SPACE_GROTESK_SEMI_BOLD, OXANIUM_REGULAR,
            OXANIUM_SEMI_BOLD, RAJDHANI_REGULAR, RAJDHANI_SEMI_BOLD);

    private BuiltinFonts() {
    }

    private static FontDescriptor font(String family, FontWeight weight, String relativePath) {
        return new FontDescriptor(family, weight, "fonts/" + relativePath);
    }
}
