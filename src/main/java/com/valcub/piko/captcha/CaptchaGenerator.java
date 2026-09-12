package com.valcub.piko.captcha;

import com.valcub.piko.layout.CharacterLayout;
import com.valcub.piko.layout.CharacterLayoutEngine;
import com.valcub.piko.random.RandomProvider;
import com.valcub.piko.render.CharacterRenderer;
import com.valcub.piko.render.CharacterStyle;
import com.valcub.piko.render.RenderContext;
import com.valcub.piko.svg.SvgDocument;

import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/** Coordinates code generation, layout, theme rendering, and glyph conversion. */
public final class CaptchaGenerator {
    private final CharacterLayoutEngine layoutEngine;
    private final CharacterRenderer characterRenderer;

    public CaptchaGenerator() {
        this(new CharacterLayoutEngine(), new CharacterRenderer());
    }

    public CaptchaGenerator(CharacterLayoutEngine layoutEngine, CharacterRenderer characterRenderer) {
        this.layoutEngine = Objects.requireNonNull(layoutEngine, "layoutEngine");
        this.characterRenderer = Objects.requireNonNull(characterRenderer, "characterRenderer");
    }

    public CaptchaResult generate(CaptchaOptions options) {
        Objects.requireNonNull(options, "options");
        return generate(options, options.newRandomProvider());
    }

    public CaptchaResult generate(CaptchaOptions options, RandomProvider random) {
        Objects.requireNonNull(options, "options");
        Objects.requireNonNull(random, "random");
        String code = generateCode(options, random);
        SvgDocument document = new SvgDocument(options.width(), options.height());
        List<CharacterLayout> layouts = layoutEngine.layout(code, options.width(), options.height(), random);
        RenderContext context = new RenderContext(document, options, code, random, layouts);

        options.theme().renderBackground(context);
        for (int index = 0; index < code.length(); index++) {
            char value = code.charAt(index);
            CharacterStyle style = options.theme().characterStyle(context, index, value);
            context.add(characterRenderer.render(value, style, options.fontRegistry()));
        }
        options.theme().renderForeground(context);
        return new CaptchaResult(UUID.randomUUID().toString(), code, document.render(), Instant.now());
    }

    private static String generateCode(CaptchaOptions options, RandomProvider random) {
        StringBuilder code = new StringBuilder(options.length());
        String characters = options.characters();
        for (int index = 0; index < options.length(); index++) {
            code.append(characters.charAt(random.nextInt(characters.length())));
        }
        return code.toString();
    }
}
