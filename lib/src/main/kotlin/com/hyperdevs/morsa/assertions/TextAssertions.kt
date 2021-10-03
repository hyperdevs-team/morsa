package com.hyperdevs.morsa.assertions

import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertContentDescriptionEquals
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.hasText

/**
 * Provides text based assertions for views
 */
interface TextAssertions : BaseAssertions {

    /**
     * Checks if the view has empty text
     */
    fun hasEmptyText() {
        node.assert(hasText("", substring = false, ignoreCase = false))
    }

    /**
     * Checks if the view has given text
     *
     * @param text Text to be matched
     */
    fun hasText(text: String) {
        node.assert(hasText(text, substring = false, ignoreCase = false))
    }

    /**
     * Checks if the view contains given text
     *
     * @param text Text to be searched
     */
    fun hasContentDescription(text: String) {
        node.assertContentDescriptionEquals(text)
    }

    /**
     * Checks if the view contains given text
     *
     * @param text Text to be searched
     */
    fun containsText(text: String) {
        node.assertTextContains(text)
    }
}