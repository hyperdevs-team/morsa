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
