package com.valcub.piko.font;

import org.junit.jupiter.api.Test;

import java.awt.Font;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class FontRegistryTest {
    @Test
    void allBundledFontResourcesExistAndCanBeLoaded() {
        FontRegistry registry = new FontRegistry(new ClasspathFontProvider());
        for (FontDescriptor descriptor : BuiltinFonts.ALL) {
            try (InputStream input = FontRegistryTest.class.getClassLoader()
                    .getResourceAsStream(descriptor.resourcePath())) {
                assertNotNull(input, descriptor.resourcePath());
                assertFalse(input.read() < 0, descriptor.resourcePath());
            } catch (Exception exception) {
                throw new AssertionError("Could not read " + descriptor.resourcePath(), exception);
            }
            Font font = assertDoesNotThrow(() -> registry.baseFont(descriptor), descriptor.resourcePath());
            assertNotNull(font);
        }
        assertEquals(9, registry.cachedFontCount());
    }

    @Test
    void appliesDescriptorWeightWhenDerivingFonts() {
        FontRegistry registry = new FontRegistry(new ClasspathFontProvider());

        Font regular = registry.derive(BuiltinFonts.LORA_REGULAR, 32f);
        Font bold = registry.derive(BuiltinFonts.LORA_BOLD, 32f);

        assertNotEquals(regular, bold);
    }

    @Test
    void bundlesFontLicenseFiles() {
        assertNotNull(FontRegistryTest.class.getClassLoader().getResourceAsStream("fonts/NOTICE.txt"));
        assertNotNull(FontRegistryTest.class.getClassLoader().getResourceAsStream("fonts/OFL-1.1.txt"));
        assertNotNull(FontRegistryTest.class.getClassLoader().getResourceAsStream("fonts/LICENSE"));
    }
}
