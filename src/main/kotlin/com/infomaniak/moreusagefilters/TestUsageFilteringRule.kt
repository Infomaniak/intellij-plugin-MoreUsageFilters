package com.infomaniak.moreusagefilters

import com.intellij.openapi.roots.TestSourcesFilter
import com.intellij.psi.PsiElement

/**
 * Hides the usages that are located in test sources, that is in the `test` and `androidTest` source sets of a Gradle
 * project, or in any directory marked as a test source root.
 */
object TestUsageFilteringRule : PsiElementUsageFilteringRule(
    id = "com.infomaniak.moreusagefilters.TestUsages",
    action = ActionIds.SHOW_TEST_USAGES,
) {

    override fun isFilteredOut(element: PsiElement): Boolean {
        val file = element.containingFile?.virtualFile ?: return false

        return TestSourcesFilter.isTestSources(file, element.project)
    }
}
