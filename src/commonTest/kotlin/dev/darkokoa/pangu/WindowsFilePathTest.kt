@file:Suppress("TestFunctionName", "NonAsciiCharacters")

package dev.darkokoa.pangu

import kotlin.test.Test

// Ported from https://github.com/vinta/pangu.js/blob/master/tests/shared/symbol-backslash.test.ts
class WindowsFilePathTest {

    @Test
    fun handleWindowsFilePath() {
        assertEqualsSpacingText("檔案在C:\\Users\\name\\", "檔案在 C:\\Users\\name\\")
        assertEqualsSpacingText("程式在D:\\Program Files\\", "程式在 D:\\Program Files\\")
        assertEqualsSpacingText("在C:\\Windows\\System32", "在 C:\\Windows\\System32")
    }
}
