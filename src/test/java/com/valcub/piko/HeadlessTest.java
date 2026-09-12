package com.valcub.piko;

import com.valcub.piko.captcha.CaptchaResult;
import org.junit.jupiter.api.Test;

import java.awt.GraphicsEnvironment;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HeadlessTest {
    @Test
    void generationWorksInHeadlessMode() {
        assertTrue(GraphicsEnvironment.isHeadless());
        CaptchaResult result = Piko.builder().seed(123).build().generate();
        assertFalse(result.svg().isBlank());
    }
}
