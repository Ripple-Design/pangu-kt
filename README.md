# pangu.kt

[![badge-version]](https://search.maven.org/search?q=g:art.rippledesign%20a:pangu*)
![badge-jvm][badge-jvm]
![badge-android][badge-android]
![badge-js][badge-js]
![badge-wasm][badge-wasm]
![badge-ios][badge-ios]
![badge-watchos][badge-watchos]
![badge-tvos][badge-tvos]
![badge-macos][badge-macos]
![badge-windows][badge-windows]
![badge-linux][badge-linux]

Kotlin Multiplatform implementation of [pangu.js](https://github.com/vinta/pangu.js).

This repository is the [Ripple-Design](https://github.com/Ripple-Design) fork of [pangu.kt](https://github.com/darkokoa/pangu.kt). The project is still pangu.kt; only the repository name uses a hyphen to follow the organization's naming rules. This fork adds the product-name, credit-rating, and blood-type suffix rules and the Windows drive path rule of pangu.js 10.4.1. It publishes as `art.rippledesign:pangu` with the `art.rippledesign.pangu` package; the package and coordinates differ from upstream, so update both when switching.

`pangu.kt` inserts spacing between CJK characters and Latin letters, numbers, or symbols.

## Install

For Kotlin Multiplatform projects:

```kotlin
kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation("art.rippledesign:pangu:<version>")
            }
        }
    }
}
```

For JVM-only projects:

```kotlin
dependencies {
    implementation("art.rippledesign:pangu-jvm:<version>")
}
```

For Android-only projects:

```kotlin
dependencies {
    implementation("art.rippledesign:pangu-android:<version>")
}
```

Android KMP consumers can also depend on the root artifact from common source sets.

## Usage

Kotlin:

```kotlin
import art.rippledesign.pangu.Pangu
import art.rippledesign.pangu.spacingText

val pendingText = "中文abc"
val completedText = Pangu.spacingText(pendingText)

val completedWithExtension = pendingText.spacingText()

val alreadySpaced = completedText.hasProperSpacing()
```

Java:

```java
import art.rippledesign.pangu.Pangu;
import art.rippledesign.pangu.PanguKt;

String pendingText = "中文abc";
String completedText = Pangu.INSTANCE.spacingText(pendingText);

String completedWithStaticFunction = PanguKt.spacingText(pendingText);

boolean alreadySpaced = Pangu.INSTANCE.hasProperSpacing(completedText);
boolean alreadySpacedFromStatic = PanguKt.hasProperSpacing(completedText);
```

## Targets

The modern KMP build publishes:

- `jvm`
- `android`
- `js`
- `wasmJs`
- `iosArm64`, `iosSimulatorArm64`, `iosX64`
- `watchosArm32`, `watchosArm64`, `watchosDeviceArm64`, `watchosSimulatorArm64`
- `tvosArm64`, `tvosSimulatorArm64`
- `macosArm64`
- `linuxX64`, `linuxArm64`
- `mingwX64`

Deprecated Kotlin/Native targets `macosX64`, `watchosX64`, and `tvosX64` are no longer published.

## Behavior

`spacingText` follows the plain-text rules of [pangu.js](https://github.com/vinta/pangu.js) 10.4.1 for the cases below. `hasProperSpacing` is true when `spacingText` would return the same string.

- Half-width `~ ! ; : , . ?` stays half-width. A space is added after it only when CJK, a letter, or a digit follows. A colon glued to a parenthesis is still converted to full-width (`前面:)後面` becomes `前面：) 後面`).
- `http://` and `https://` URLs are kept intact, including percent-encoding and CJK inside the URL. A space is added on the CJK side.
- `/` is not an operator. `前面/後面` stays tight. Recognized Unix paths such as `/home` and `./docs` are spaced as one unit.
- `+`, `-`, and `|` are separators only when that symbol touches CJK on the line. Otherwise they stay joiners (`A+B`, `1-10`, `x|y`). Affixes stay attached: `+886`, `100+`, `A+`, `D-`, `-m`. `C++` stays intact.
- A single tight interpunct (`·`, `•`, `‧`) becomes `・`. A spaced interpunct, or a mask such as `••••`, is left as written.
- Letterlike symbols (℃、Ω、ℓ) and Dingbats (✂、✅) are spaced like other symbols. Superscripts, `™`, `℠`, and `®` stay attached on the left. `©` is spaced from a following year.
- An em dash (`—`, U+2014) is not a symbol that gets spaces. `他說——不對` stays tight.
- Text inside backticks is not spaced. Quotes may span a line. A straight single quote around only CJK stays tight (`'铁蕾'`). A call parenthesis after a dotted name stays tight (`addEventListener(`).
- An HTML tag with an ASCII name and a closing `>` is left intact, including the tag name and attributes. Text between tags is spaced. A double-quoted attribute value is spaced (`value="測試123"` becomes `value="測試 123"`). A void tag such as `<br>`, `<hr>`, or `<img>` stays tight against neighboring CJK. A bare non-void tag with no closer (`<div>`, `<String>`, `<Spinner />`) is a mention and takes a space from adjacent CJK. `<` and `>` that are not a tag, including `<!-- -->`, stay brackets.
- `#` followed by a space or a non-breaking space is not a hashtag. `/#tag` in a slash list stays tight. `C#` still takes a space before following CJK.
- Product names and tiers with a `+` suffix keep the sign attached at a CJK boundary (`Disney+`, `Apple TV+`, `公視+`, `影劇館+`, `Pro+`). Credit ratings (`AA+`, `BBB-`, `twAA+`) and blood types (`AB+`, `AB-`, `Rh+`) are covered the same way. A closing punctuation mark follows the suffix tight; a word or an opening bracket takes the boundary space.
- Windows drive paths are spaced as one unit from CJK, for example `檔案在C:\Users\name\` becomes `檔案在 C:\Users\name\`.

## Notes

Processing time grows with input size. Very large text should be processed off the UI thread.

Java support requires Java 8 bytecode compatibility or newer.

## License

Released under the [MIT License](LICENSE).

[badge-version]: https://img.shields.io/maven-central/v/art.rippledesign/pangu?style=flat
[badge-ios]: https://img.shields.io/badge/platform-ios-CDCDCD.svg?style=flat
[badge-js]: https://img.shields.io/badge/platform-js-F8DB5D.svg?style=flat
[badge-jvm]: https://img.shields.io/badge/platform-jvm-DB413D.svg?style=flat
[badge-android]: https://img.shields.io/badge/platform-android-6EDB8D.svg?style=flat
[badge-wasm]: https://img.shields.io/badge/platform-wasm-624FE8.svg?style=flat
[badge-linux]: https://img.shields.io/badge/platform-linux-2D3F6C.svg?style=flat
[badge-windows]: https://img.shields.io/badge/platform-windows-4D76CD.svg?style=flat
[badge-macos]: https://img.shields.io/badge/platform-macos-111111.svg?style=flat
[badge-watchos]: https://img.shields.io/badge/platform-watchos-C0C0C0.svg?style=flat
[badge-tvos]: https://img.shields.io/badge/platform-tvos-808080.svg?style=flat
