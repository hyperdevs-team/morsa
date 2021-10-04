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

package com.hyperdevs.morsa.assertions

import androidx.compose.ui.test.*

/**
 * Base interface for asserting views
 *
 * Provides basic assertions that can be performed on any view
 *
 * @see TextAssertions
 */
@Suppress("TooManyFunctions")
interface BaseAssertions {
    val node: SemanticsNodeInteraction

    /**
     * Checks if the view is displayed
     */
    fun isDisplayed() {
        node.assertIsDisplayed()
    }

    /**
     * Checks if the view is not displayed
     */
    fun isNotDisplayed() {
        node.assertIsNotDisplayed()
    }

    /**
     * Checks if the view is selected
     */
    fun isSelected() {
        node.assertIsSelected()
    }

    /**
     * Checks if the view is selectable
     */
    fun isSelectable() {
        node.assertIsSelectable()
    }

    /**
     * Checks if the view is not selected
     */
    fun isNotSelected() {
        node.assertIsNotSelected()
    }

    /**
     * Checks if the view is focused
     */
    fun isFocused() {
        node.assertIsFocused()
    }

    /**
     * Checks if the view is not focused
     */
    fun isNotFocused() {
        node.assertIsNotFocused()
    }

    /**
     * Checks if the view is clickable
     */
    fun isClickable() {
        node.assertHasClickAction()
    }

    /**
     * Checks if the view is not clickable
     */
    fun isNotClickable() {
        node.assertHasNoClickAction()
    }

    /**
     * Checks if the view is enabled
     */
    fun isEnabled() {
        node.assertIsEnabled()
    }

    /**
     * Checks if the view is not enabled
     */
    fun isNotEnabled() {
        node.assertIsNotEnabled()
    }

    /**
     * Checks if the view has given tag
     *
     * @param testTag Tag that view must have
     */
    fun hasTag(testTag: String) {
        node.assert(hasTestTag(testTag))
    }

    /**
     * Checks if the matched view exist
     */
    fun exist(vararg tags: String) {
        node.assertExists()
    }

    /**
     * Checks if the matched view does not exist
     */
    fun doesNotExist(vararg tags: String) {
        node.assertDoesNotExist()
    }

    /**
     * Checks if the view has given descendant
     *
     * @param matcher SemanticsMatcher to be use to match descendants
     */
    fun hasDescendant(matcher: SemanticsMatcher) {
        node.assert(hasAnyDescendant(matcher))
    }

    /**
     * Checks if the view has given ancestor
     *
     * @param matcher SemanticsMatcher to be use to match ancestors
     */
    fun hasAncestor(matcher: SemanticsMatcher) {
        node.assert(hasAnyAncestor(matcher))
    }

    /**
     * Checks if the view has given child
     *
     * @param matcher SemanticsMatcher to be use to match children
     */
    fun hasChild(matcher: SemanticsMatcher) {
        node.assert(hasAnyChild(matcher))
    }

    /**
     * Checks if the view has given sibling
     *
     * @param matcher SemanticsMatcher to be use to match siblings
     */
    fun hasSibling(matcher: SemanticsMatcher) {
        node.assert(hasAnySibling(matcher))
    }

    /**
     * Check the view with the given custom assertion matcher
     *
     * @param matcher SemanticsMatcher to assert the view with
     */
    fun assert(matcher: SemanticsMatcher) {
        node.assert(matcher)
    }
}
