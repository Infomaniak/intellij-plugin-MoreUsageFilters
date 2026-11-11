package com.infomaniak.moreusagefilters

import com.intellij.lang.injection.InjectedLanguageManager
import com.intellij.psi.PsiElement

/**
 * Hides the usages that are located in Markdown files, such as a README, including the ones in their code fences.
 */
object MarkdownUsageFilteringRule : PsiElementUsageFilteringRule(
    id = "com.infomaniak.moreusagefilters.MarkdownUsages",
    action = ActionIds.SHOW_MARKDOWN_USAGES,
) {

    private val MARKDOWN_EXTENSIONS = setOf("md", "markdown")

    override fun isFilteredOut(element: PsiElement): Boolean {
        // A code fence is injected as a fragment of its own language, the Markdown file is the one hosting it.
        val file = InjectedLanguageManager.getInstance(element.project).getTopLevelFile(element) ?: return false

        return file.name.substringAfterLast('.', "").lowercase() in MARKDOWN_EXTENSIONS
    }
}
