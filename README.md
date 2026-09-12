# Piko

Piko is a headless Java 17 library that generates self-contained SVG character
captchas. Every glyph is converted from a Java2D `GlyphVector` outline into an
SVG `<path>`; generated SVG never contains a `<text>` element, plaintext code,
external image, external font, or runtime network request.

## Features

- Java 17 and Maven; no framework or servlet dependency.
- Thread-safe `PikoCaptcha` facade suitable for singleton application wiring.
- Secure randomness by default and reproducible output with `seed(long)`.
- Eight built-in themes: watercolor, starry night, glass, botanical, geometric,
  cute, ink, and cyber.
- Nine bundled classpath TTF resources under `src/main/resources/fonts`.
- Allow-listed SVG tags and attributes with XML escaping and external-value
  rejection.
- A static gallery demo that creates 24 SVG files and an `index.html`.

## Quick start

```java
import com.valcub.piko.captcha.CaptchaResult;
import com.valcub.piko.captcha.PikoCaptcha;
import com.valcub.piko.theme.PikoTheme;

CaptchaResult result = PikoCaptcha.builder()
        .width(220)
        .height(80)
        .length(5)
        .theme(PikoTheme.STARRY_NIGHT)
        .seed(42L)       // omit this for SecureRandom-backed output
        .build()
        .generate();

String code = result.code();
String svg = result.svg();
```

The static convenience API is also available:

```java
CaptchaResult result = com.valcub.piko.Piko.generate();
```

## Installation

For Maven:

```xml
<dependency>
    <groupId>com.valcub.piko</groupId>
    <artifactId>piko</artifactId>
    <version>0.1.0-SNAPSHOT</version>
</dependency>
```

Piko is a plain Java library and does not pull in Spring Boot, a servlet API,
or a browser runtime.

## Preview

Run `mvn exec:java` to create the local gallery in `target/piko-demo`. It
contains three deterministic examples for every built-in theme and an
`index.html` Grid layout for side-by-side visual inspection.

## Build and demo

```bash
mvn test
mvn package
mvn exec:java
```

The demo writes `target/piko-demo/index.html` and three SVG samples for each
theme. Open that local file in a browser to compare the visual systems.

## Configuration

`CaptchaOptions` validates dimensions, code length, alphabet contents, and
font selection before a request starts:

```java
CaptchaResult result = PikoCaptcha.builder()
        .characters("ABCDEFGHJKLMNPQRSTUVWXYZ23456789")
        .caseSensitive(false)
        .noiseLevel(com.valcub.piko.captcha.NoiseLevel.LOW)
        .backgroundTexture(true)
        .foregroundCurve(true)
        .fontSelectionStrategy(com.valcub.piko.font.FontSelectionStrategy.SINGLE_FONT)
        .theme(PikoTheme.BOTANICAL)
        .build()
        .generate();
```

`caseSensitive(false)` normalizes the configured alphabet to uppercase and
deduplicates repeated characters. The default alphabet omits commonly
confused glyphs such as `I`, `O`, `0`, and `1`.

## Font system

`FontRegistry` loads only classpath resources through
`ClasspathFontProvider`. It does not scan OS fonts, invoke a desktop API, or
download anything. Each resource is parsed once per registry in a concurrent
map; descriptor weight is applied when deriving the request-sized `Font` before
creating its glyph vector. A custom `FontProvider` can be supplied to
`FontRegistry` when an application owns a separately managed, trusted font
bundle.

## Security model

The SVG builder supports a deliberately small element and attribute allow-list.
Event attributes, `href`/`src`, `style`, `xml:base`, unknown attributes,
executable schemes, `data:` values, protocol-relative values, and HTTP(S)
values are rejected. All serialized attribute values pass through XML escaping.
Theme artwork uses only local primitives, gradients, filters, and paths. The
code is returned in `CaptchaResult` for server-side verification; it is
intentionally not embedded in the SVG markup.

## Spring Boot example

The library has no web framework coupling. An endpoint can generate the result
and save the code in a short-lived server-side session:

```java
@GetMapping(value = "/captcha.svg", produces = "image/svg+xml")
String captcha(HttpSession session) {
    CaptchaResult result = PikoCaptcha.builder().build().generate();
    session.setAttribute("captcha", result.code());
    return result.svg();
}
```

For Redis or another shared cache, store `result.code()` with a short TTL and
an unguessable result id. The core library remains independent of both session
and cache implementations.

## Servlet example

The same result can be returned from a servlet without adding a servlet
dependency to the Piko library:

```java
CaptchaResult result = captcha.generate();
request.getSession().setAttribute("captcha", result.code());
response.setContentType("image/svg+xml;charset=UTF-8");
response.setHeader("Cache-Control", "no-store");
response.getWriter().write(result.svg());
```

For a production service, compare a submitted code against the server-side
value using a constant-time comparison and expire it after one successful
attempt. Do not trust a code supplied by the browser.

## Custom theme

Implement `CaptchaTheme` to add an application-specific visual system without
changing `CaptchaGenerator`:

```java
CaptchaTheme theme = new CaptchaTheme() {
    public String id() { return "brand"; }
    public void renderBackground(RenderContext context) { /* add primitives */ }
    public CharacterStyle characterStyle(RenderContext context, int index, char value) {
        CharacterLayout slot = context.layout(index);
        return new CharacterStyle(BuiltinFonts.LORA_REGULAR, 42, "#203040", 0,
                1, 1, 1, slot.centerX(), slot.centerY());
    }
    public void renderForeground(RenderContext context) { /* add curves */ }
};
CaptchaResult result = PikoCaptcha.builder().theme(theme).build().generate();
```

## Performance notes

Font parsing is amortized by `FontRegistry`, and secure random state is
thread-local so it is initialized once per worker thread rather than once per
request. `PikoCaptcha` is immutable and safe to share, while each generation
keeps layout and SVG mutation local to the request. The default canvas is
220×80; reducing the number of decorations with `NoiseLevel.LOW` or `NONE` is
useful for high-volume services. The repository's ad-hoc baseline is documented
in the review report; use JMH for release capacity planning.

## License

Piko source code is licensed under the Apache License 2.0. Bundled font files
retain the licenses of their original families; see
`src/main/resources/fonts/NOTICE.txt`, the bundled `OFL-1.1.txt`, and
`fonts/LICENSE` inside the binary package before redistributing it.
