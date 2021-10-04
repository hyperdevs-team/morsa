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

package com.hyperdevs.morsa.screen

import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.hyperdevs.morsa.actions.BaseActions
import com.hyperdevs.morsa.actions.EditableActions
import com.hyperdevs.morsa.actions.TextActions
import com.hyperdevs.morsa.assertions.BaseAssertions
import com.hyperdevs.morsa.assertions.TextAssertions

/**
 * Container class for UI elements.
 *
 * This class groups UI elements and grants access to basic actions,
 * such as awaitIdle()
 *
 * @param T type of your screen, done to enable invoke() for its children
 *
 *  I'M A MOTHERFUCKIN' WALRUS
 *
 *                      /
 *              ___    /
 *           .-9 9 `\
 *         =(:(::)=  ;
 *           ||||     \
 *           ||||      `-.
 *          ,\|\|         `,
 *         /                \
 *        ;                  `'---.,
 *        |                         `\
 *        ;                     /     |
 *        \                    |      /
 * jgs     )           \  __,.--\    /
 *      .-' \,..._\     \`   .-'  .-'
 *     `-=``      `:    |   /-/-/`
 *                  `.__/
 */
open class MorsaScreen<out T : MorsaScreen<T>>(private val composeTestRule: ComposeContentTestRule) {

    /**
     * Suspends until compose is idle. Compose is idle if there are no pending compositions, no
     * pending changes that could lead to another composition, and no pending draw calls.
     *
     * In case the main clock auto advancement is enabled (by default is) this will also keep
     * advancing the clock until it is idle (meaning there are no recompositions, animations, etc.
     * pending). If not, this will wait only for other idling resources.
     */
    suspend fun awaitIdle() {
        composeTestRule.awaitIdle()
    }

    /**
     * Operator that allows usage of DSL style
     */
    operator fun invoke(function: T.() -> Unit) {
        function(this as T)
    }

    /**
     *  INNER VIEW CLASSES
     *
     *  These classes are marked as inner to avoid exposing outside the compose test rule. We could move it out if we find a way
     *  to make use of the rule(or just encapsulate it inside the screen).
     */

    /**
     * View with BaseActions and EditableActions
     */
    inner class MTextField(override val node: SemanticsNodeInteraction) : MBaseView<MTextField>(node),
        EditableActions, TextAssertions {
        constructor(function: ComposeContentTestRule.() -> SemanticsNodeInteraction) : this(composeTestRule.function())
    }

    /**
     * View with BaseActions and TextActions
     */
    inner class MText(override val node: SemanticsNodeInteraction) : MBaseView<MText>(node),
        TextActions, TextAssertions {
        constructor(function: ComposeContentTestRule.() -> SemanticsNodeInteraction) : this(composeTestRule.function())
    }

    /**
     * Base class for all Morsa views
     *
     * This base class allows create new custom view with ease. All you
     * have to do is to extend this class, implement all necessarily additional
     * actions/assertions interfaces and override necessary constructors
     *
     * @param T Type of your custom view. Needs to be defined to enable invoke() and perform() for descendants
     */
    open inner class MBaseView<out T>(override val node: SemanticsNodeInteraction) : BaseActions,
        BaseAssertions {

        operator fun invoke(function: T.() -> Unit) {
            function(this as T)
        }

        infix fun perform(function: T.() -> Unit): T {
            function(this as T)
            return this
        }
    }

    /**
     *  HELPERS
     *
     *  Syntactic sugar (:3 っ)っ
     */
    protected fun ComposeContentTestRule.withTag(tag: String): SemanticsNodeInteraction {
        return this.onNodeWithTag(tag)
    }

    protected fun ComposeContentTestRule.withText(text: String): SemanticsNodeInteraction {
        return this.onNodeWithText(text)
    }

    protected fun ComposeContentTestRule.withContentDescription(value: String): SemanticsNodeInteraction {
        return this.onNodeWithContentDescription(value)
    }
}
