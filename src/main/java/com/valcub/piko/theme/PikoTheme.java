package com.valcub.piko.theme;

import com.valcub.piko.font.BuiltinFonts;
import com.valcub.piko.font.FontDescriptor;
import com.valcub.piko.layout.CharacterLayout;
import com.valcub.piko.render.CharacterStyle;
import com.valcub.piko.render.RenderContext;

import java.util.List;

/** The eight built-in illustration systems shipped by Piko. */
public enum PikoTheme implements CaptchaTheme {
    WATERCOLOR("watercolor",
            new ThemePalette(List.of("#253B45", "#4F5963", "#7B4051"),
                    List.of("#E9A6A6", "#A8C7C1", "#E8C77B", "#B7B1D8"),
                    List.of("#FBF6E9"), List.of("#D8C9B2", "#C5D8D1", "#E4BDB0")),
            List.of(BuiltinFonts.LORA_REGULAR, BuiltinFonts.LORA_SEMI_BOLD, BuiltinFonts.MERRIWEATHER_REGULAR), 6,
            new WatercolorArtwork()),
    STARRY_NIGHT("starry-night",
            new ThemePalette(List.of("#FFF5C2", "#E6EEFF", "#B8D7FF"),
                    List.of("#F8E8A6", "#A8C8FF", "#D5B7FF"),
                    List.of("#07152F", "#102A56"), List.of("#34558A", "#566C9F", "#A38FD0")),
            List.of(BuiltinFonts.PLAYFAIR_DISPLAY_REGULAR, BuiltinFonts.PLAYFAIR_DISPLAY_SEMI_BOLD,
                    BuiltinFonts.LORA_REGULAR), 8, new StarryNightArtwork()),
    GLASS("glass",
            new ThemePalette(List.of("#304B73", "#5B3D73", "#73495A"),
                    List.of("#FFFFFF", "#B7D9FF", "#E3B8FF", "#FFD1C2"),
                    List.of("#D8E8FF", "#F7DDF0"), List.of("#A7C6E8", "#D8B8DC", "#F0C4B8")),
            List.of(BuiltinFonts.LIBRE_BASKERVILLE_REGULAR, BuiltinFonts.LIBRE_BASKERVILLE_BOLD,
                    BuiltinFonts.LORA_REGULAR), 5, new GlassArtwork()),
    BOTANICAL("botanical",
            new ThemePalette(List.of("#274F3B", "#385C45", "#68483B"),
                    List.of("#7B9A65", "#B7C98A", "#D7A45A", "#9A6B58"),
                    List.of("#F3F0DE"), List.of("#B3B98D", "#D4BE91", "#B89477")),
            List.of(BuiltinFonts.LORA_REGULAR, BuiltinFonts.LORA_BOLD, BuiltinFonts.LIBRE_BASKERVILLE_REGULAR), 5,
            new BotanicalArtwork()),
    GEOMETRIC("geometric",
            new ThemePalette(List.of("#16243A", "#243B5A", "#4B315C"),
                    List.of("#FA6F61", "#3A86FF", "#FFBE0B", "#8338EC"),
                    List.of("#F2F4F7"), List.of("#AEB8C5", "#D2A49E", "#AEBFE0")),
            List.of(BuiltinFonts.SPACE_GROTESK_REGULAR, BuiltinFonts.SPACE_GROTESK_SEMI_BOLD,
                    BuiltinFonts.LIBRE_BASKERVILLE_REGULAR), 4, new GeometricArtwork()),
    CUTE("cute",
            new ThemePalette(List.of("#603C57", "#4E5068", "#704E31"),
                    List.of("#FF9FB2", "#FFD166", "#9AD7D1", "#B8A1E5"),
                    List.of("#FFF3E8"), List.of("#F2C6C5", "#EAD39A", "#B9DCD5")),
            List.of(BuiltinFonts.FREDOKA_REGULAR, BuiltinFonts.FREDOKA_SEMI_BOLD, BuiltinFonts.LORA_REGULAR), 7,
            new CuteArtwork()),
    INK("ink",
            new ThemePalette(List.of("#161A1B", "#252B2D", "#3D3027"),
                    List.of("#202426", "#52585B", "#8A7560", "#B8A58E"),
                    List.of("#EEE9DF"), List.of("#A99C8B", "#C8B9A5", "#777A78")),
            List.of(BuiltinFonts.MERRIWEATHER_REGULAR, BuiltinFonts.MERRIWEATHER_BOLD,
                    BuiltinFonts.ROBOTO_SLAB_REGULAR), 4, new InkArtwork()),
    CYBER("cyber",
            new ThemePalette(List.of("#C5FFF7", "#C5E8FF", "#FFD5F0"),
                    List.of("#00F5D4", "#00BBF9", "#F15BB5", "#FEE440"),
                    List.of("#080B1A"), List.of("#17324B", "#244B62", "#6A2459")),
            List.of(BuiltinFonts.OXANIUM_REGULAR, BuiltinFonts.OXANIUM_SEMI_BOLD,
                    BuiltinFonts.RAJDHANI_SEMI_BOLD, BuiltinFonts.SPACE_GROTESK_SEMI_BOLD), 3,
            new CyberArtwork());

    private final String id;
    private final ThemePalette palette;
    private final List<FontDescriptor> fontPool;
    private final double maxRotationDegrees;
    private final ThemeArtwork artwork;

    PikoTheme(String id, ThemePalette palette, List<FontDescriptor> fontPool,
              double maxRotationDegrees, ThemeArtwork artwork) {
        this.id = id;
        this.palette = palette;
        this.fontPool = List.copyOf(fontPool);
        this.maxRotationDegrees = maxRotationDegrees;
        this.artwork = artwork;
    }

    @Override
    public String id() {
        return id;
    }

    public ThemePalette palette() {
        return palette;
    }

    public List<FontDescriptor> fontPool() {
        return fontPool;
    }

    public double maxRotationDegrees() {
        return maxRotationDegrees;
    }

    @Override
    public void renderBackground(RenderContext context) {
        artwork.renderBackground(context);
    }

    @Override
    public CharacterStyle characterStyle(RenderContext context, int index, char value) {
        CharacterLayout layout = context.layout(index);
        double size = Math.min(context.height() * 0.64, layout.slotWidth() * 1.42);
        size *= context.random().nextDouble(0.91, 1.05);
        double scaleX = context.random().nextDouble(0.92, 1.06);
        double scaleY = context.random().nextDouble(0.94, 1.06);
        if (this == CYBER) {
            scaleX = context.random().nextDouble(0.84, 1.02);
        }
        String fill = context.random().pick(palette.characterColors());
        double rotation = context.random().nextDouble(-maxRotationDegrees, maxRotationDegrees);
        double opacity = context.random().nextDouble(0.91, 1.0);
        return new CharacterStyle(context.chooseFont("characters", fontPool), (float) size, fill, rotation,
                scaleX, scaleY, opacity, layout.centerX(), layout.centerY());
    }

    @Override
    public void renderForeground(RenderContext context) {
        if (context.options().foregroundCurve()) {
            artwork.renderForeground(context);
        }
    }
}
