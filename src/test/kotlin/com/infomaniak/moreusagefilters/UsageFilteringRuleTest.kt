package com.infomaniak.moreusagefilters

import com.intellij.testFramework.PsiTestUtil
import com.intellij.testFramework.fixtures.BasePlatformTestCase
import com.intellij.usageView.UsageInfo
import com.intellij.usages.UsageInfo2UsageAdapter
import com.intellij.usages.UsageTarget

class UsageFilteringRuleTest : BasePlatformTestCase() {

    fun `test the preview rule hides a usage located in a preview function`() {
        val usage = usageAtCaret(
            """
            @Preview
            @Composable
            fun MyButtonPreview() {
                MyBut<caret>ton()
            }
            """.trimIndent()
        )

        assertFalse(PreviewUsageFilteringRule.isVisible(usage, UsageTarget.EMPTY_ARRAY))
    }

    fun `test the preview rule keeps a usage located in a regular function`() {
        val usage = usageAtCaret(
            """
            @Composable
            fun MyScreen() {
                MyBut<caret>ton()
            }
            """.trimIndent()
        )

        assertTrue(PreviewUsageFilteringRule.isVisible(usage, UsageTarget.EMPTY_ARRAY))
    }

    fun `test the test rule hides a usage located in a test source root`() {
        val testRoot = myFixture.tempDirFixture.findOrCreateDir("test")
        PsiTestUtil.addSourceRoot(module, testRoot, true)

        try {
            val usage = usageAtCaret(text = USAGE_IN_CODE, path = "test/SampleTest.kt")

            assertFalse(TestUsageFilteringRule.isVisible(usage, UsageTarget.EMPTY_ARRAY))
        } finally {
            PsiTestUtil.removeSourceRoot(module, testRoot)
        }
    }

    fun `test the test rule keeps a usage located in a production source root`() {
        val usage = usageAtCaret(text = USAGE_IN_CODE, path = "main/Sample.kt")

        assertTrue(TestUsageFilteringRule.isVisible(usage, UsageTarget.EMPTY_ARRAY))
    }

    private fun usageAtCaret(text: String, path: String = "Sample.kt"): UsageInfo2UsageAdapter {
        val file = myFixture.addFileToProject(path, text)
        myFixture.configureFromExistingVirtualFile(file.virtualFile)
        val element = file.findElementAt(myFixture.caretOffset)!!

        return UsageInfo2UsageAdapter(UsageInfo(element))
    }

    private companion object {
        val USAGE_IN_CODE = """
            fun MyScreen() {
                MyBut<caret>ton()
            }
        """.trimIndent()
    }
}
