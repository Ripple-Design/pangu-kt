@file:Suppress("TestFunctionName", "NonAsciiCharacters")

package dev.darkokoa.pangu

import kotlin.test.Test

// Ported from https://github.com/vinta/pangu.js/blob/master/tests/shared/name-suffix.test.ts
class NameSuffixTest {

    @Test
    fun handleProductNamesWithSuffixes() {
        assertEqualsSpacingText("Apple Fitness+推出新課程", "Apple Fitness+ 推出新課程")
        assertEqualsSpacingText("Apple TV+上架了新片", "Apple TV+ 上架了新片")
        assertEqualsSpacingText("Discovery+和discovery+都上架了", "Discovery+ 和 discovery+ 都上架了")
        assertEqualsSpacingText("Disney+上架了新片", "Disney+ 上架了新片")
        assertEqualsSpacingText("Disney+上架了C++課程", "Disney+ 上架了 C++ 課程")
        assertEqualsSpacingText("mo店+免運無限次 天天超取290起-momo購物網", "mo 店+ 免運無限次 天天超取 290 起 - momo 購物網")
        assertEqualsSpacingText("mo 店+ 免運無限次", "mo 店+ 免運無限次")
        assertEqualsSpacingText("PS+會員", "PS+ 會員")
        assertEqualsSpacingText("公視+上架了新片", "公視+ 上架了新片")
        assertEqualsSpacingText("如何使用PTS+（公視+）註冊與觀看？", "如何使用 PTS+（公視+）註冊與觀看？")
        assertEqualsSpacingText("公視+(免費平台)", "公視+ (免費平台)")
        assertEqualsSpacingText("今天來看公視+", "今天來看公視+")
        assertEqualsSpacingText("MOD影劇館+上架了新片", "MOD 影劇館+ 上架了新片")
        assertEqualsSpacingText(
            "Netflix、Disney+、Apple TV+、MOD影劇館+、公視+等串流平台",
            "Netflix、Disney+、Apple TV+、MOD 影劇館+、公視+ 等串流平台",
        )
    }

    @Test
    fun handleProductTiersWithSuffixes() {
        assertEqualsSpacingText("vivo X70 Pro+開賣", "vivo X70 Pro+ 開賣")
    }

    @Test
    fun handleCreditRatingsWithSuffixes() {
        assertEqualsSpacingText("評等介於AA-和AA+之間", "評等介於 AA- 和 AA+ 之間")
        assertEqualsSpacingText("惠譽給予BBB+評等", "惠譽給予 BBB+ 評等")
        assertEqualsSpacingText("惠譽給予BBB-評等", "惠譽給予 BBB- 評等")
        assertEqualsSpacingText("中華信評給予twAA+評等", "中華信評給予 twAA+ 評等")
    }

    @Test
    fun handleBloodTypesWithSuffixes() {
        assertEqualsSpacingText("血型是AB+的人", "血型是 AB+ 的人")
        assertEqualsSpacingText("血型是AB-的人", "血型是 AB- 的人")
        assertEqualsSpacingText("血型是Rh+的人", "血型是 Rh+ 的人")
        assertEqualsSpacingText("血型是Rh-的人", "血型是 Rh- 的人")
        assertEqualsSpacingText("型號AB-123的零件，血型是AB-的人", "型號 AB-123 的零件，血型是 AB- 的人")
    }

    @Test
    fun handleClosingPunctuationTightAfterSuffixes() {
        assertEqualsSpacingText("公視+，今天有新片", "公視+，今天有新片")
        assertEqualsSpacingText("公視+。", "公視+。")
        assertEqualsSpacingText("(公視+）", "(公視+）")
        assertEqualsSpacingText("[公視+]", "[公視+]")
        assertEqualsSpacingText("「公視+」", "「公視+」")
    }

    @Test
    fun handleNonPreservedNames() {
        assertEqualsSpacingText("私視+上線", "私視 + 上線")
        assertEqualsSpacingText("Disney-上架了新片", "Disney - 上架了新片")
    }
}
