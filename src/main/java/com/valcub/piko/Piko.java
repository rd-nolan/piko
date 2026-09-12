package com.valcub.piko;

import com.valcub.piko.captcha.CaptchaOptions;
import com.valcub.piko.captcha.CaptchaResult;
import com.valcub.piko.captcha.PikoCaptcha;

/** Static convenience entry point for the Piko SVG captcha library. */
public final class Piko {
    private Piko() {
    }

    public static CaptchaResult generate() {
        return PikoCaptcha.builder().build().generate();
    }

    public static CaptchaResult generate(CaptchaOptions options) {
        return new PikoCaptcha(options).generate();
    }

    public static PikoCaptcha.Builder builder() {
        return PikoCaptcha.builder();
    }
}
