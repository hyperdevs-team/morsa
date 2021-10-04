/*
 * Copyright 2021 HyperDevs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
