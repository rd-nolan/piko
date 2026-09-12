package com.valcub.piko.font;

import java.io.IOException;

public interface FontProvider {
    FontResource load(FontDescriptor descriptor) throws IOException;
}
