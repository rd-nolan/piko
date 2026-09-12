package com.valcub.piko.captcha;

import com.valcub.piko.Piko;
import com.valcub.piko.theme.PikoTheme;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CaptchaGeneratorTest {
    @Test
    void defaultApiProducesSelfContainedSvg() {
        CaptchaResult result = PikoCaptcha.builder().seed(42).build().generate();

        assertEquals(4, result.code().length());
        assertTrue(result.svg().startsWith("<svg"));
        assertTrue(result.svg().contains("viewBox=\"0 0 220 80\""));
        assertTrue(result.svg().contains("<path"));
        assertFalse(result.svg().contains("<text"));
        assertFalse(result.svg().contains(result.code()));
    }

    @Test
    void optionsControlCodeAndCanvas() {
        CaptchaResult result = PikoCaptcha.builder()
                .width(320).height(96).length(7).characters("abc123")
                .caseSensitive(true).theme(PikoTheme.CYBER).seed(7).build().generate();

        assertEquals(7, result.code().length());
        assertTrue(result.code().chars().allMatch(value -> "abc123".indexOf(value) >= 0));
        assertTrue(result.svg().contains("viewBox=\"0 0 320 96\""));
    }

    @Test
    void aSeedMakesTheRenderedArtifactRepeatable() {
        CaptchaOptions options = CaptchaOptions.builder().seed(991).theme(PikoTheme.BOTANICAL).build();

        CaptchaResult first = Piko.generate(options);
        CaptchaResult second = Piko.generate(options);

        assertEquals(first.code(), second.code());
        assertEquals(first.svg(), second.svg());
        assertNotEquals(first.id(), second.id());
    }

    @Test
    void differentSeedsChangeTheArtifact() {
        CaptchaResult first = PikoCaptcha.builder().seed(1).build().generate();
        CaptchaResult second = PikoCaptcha.builder().seed(2).build().generate();

        assertNotEquals(first.svg(), second.svg());
    }
}
