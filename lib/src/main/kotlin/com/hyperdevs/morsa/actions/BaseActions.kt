package com.hyperdevs.morsa.actions

import androidx.compose.ui.test.*

/**
 * Base interface for performing actions on view.
 * Provides a lot of basic action methods, such as click(), scrollTo(), etc.
 */
interface BaseActions {
    val node: SemanticsNodeInteraction

    /**
     * Performs click on view
     */
    fun click() {
        node.performClick()
    }

    /**
     * Performs double click on view
     */
    fun doubleClick() {
        node.performGesture { this.doubleClick() }
    }

    /**
     * Performs long click on view
     */
    fun longClick() {
        node.performGesture { this.longClick() }
    }

    /**
     * Presses IME action, if supported view is in focus
     */
    fun pressImeAction() {
        node.performImeAction()
    }

    /**
     * Scrolls to the view, if possible
     */
    fun scrollTo() {
        node.performScrollTo()
    }

    /**
     * Performs custom action on a view
     *
     * @param gestureFunc Lambda which provides a GestureScope scope to work with
     */
    fun performGesture(gestureFunc: GestureScope.() -> Unit) {
        node.performGesture(gestureFunc)
    }
}