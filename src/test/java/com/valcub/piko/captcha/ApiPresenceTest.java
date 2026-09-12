package com.valcub.piko.captcha;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class ApiPresenceTest {
    @Test
    void exposesThePublicCaptchaApi() {
        assertDoesNotThrow(() -> Class.forName("com.valcub.piko.Piko"));
        assertDoesNotThrow(() -> Class.forName("com.valcub.piko.captcha.PikoCaptcha"));
        assertDoesNotThrow(() -> Class.forName("com.valcub.piko.captcha.CaptchaOptions"));
        assertDoesNotThrow(() -> Class.forName("com.valcub.piko.captcha.CaptchaResult"));
    }
}
