package com.hyperdevs.morsa.actions

import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.performTextReplacement
import com.hyperdevs.morsa.actions.BaseActions

/**
 * Provides editable actions for views
 */
interface EditableActions : BaseActions {

    /**
     * Types the given text into the view
     *
     * @param text Text to input
     */
    fun typeText(text: String) {
        node.performTextInput(text)
    }

    /**
     * Replaces the current view text with given
     *
     * @param text Text to input instead of current
     */
    fun replaceText(text: String) {
        node.performTextReplacement(text)
    }

    /**
     * Clears current text in the view
     */
    fun clearText() {
        node.performTextClearance()
    }
}