package com.valcub.piko.theme;

import com.valcub.piko.render.CharacterStyle;
import com.valcub.piko.render.RenderContext;

/** Theme contract: background first, per-character style, and foreground last. */
public interface CaptchaTheme {
    String id();

    void renderBackground(RenderContext context);

    CharacterStyle characterStyle(RenderContext context, int index, char value);

    void renderForeground(RenderContext context);
}
