package com.valcub.piko.theme;

import com.valcub.piko.render.RenderContext;

interface ThemeArtwork {
    void renderBackground(RenderContext context);

    void renderForeground(RenderContext context);
}
