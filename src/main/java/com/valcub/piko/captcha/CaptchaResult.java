package com.valcub.piko.captcha;

import java.time.Instant;
import java.util.Objects;

/** The generated code and its self-contained SVG representation. */
public record CaptchaResult(String id, String code, String svg, Instant createdAt) {
    public CaptchaResult {
        Objects.requireNonNull(id, "id");
        Objects.requireNonNull(code, "code");
        Objects.requireNonNull(svg, "svg");
        Objects.requireNonNull(createdAt, "createdAt");
        if (id.isBlank() || code.isEmpty() || svg.isBlank()) {
            throw new IllegalArgumentException("Captcha result fields cannot be blank");
        }
    }
}
