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

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.unit.Density
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
     * Current composable android context.
     */
    protected lateinit var context: Context

    /**
     * Current device screen's density.
     */
    val density: Density = composeTestRule.density

    /**
     * Clock that drives frames and recompositions in compose tests.
     */
    val mainClock: MainTestClock = composeTestRule.mainClock

    /**
     * Sets the given composable as a content of the current screen.
     * It also sets the value to the [MorsaScreen.context] to be used without the
     * need of a composable scope.
     *
     * Use this in your tests to setup the UI content to be tested. This should be called exactly
     * once per test.
     *
     * @throws IllegalStateException if called more than once per test.
     */
    fun setContent(composable: @Composable () -> Unit) {
        composeTestRule.setContent {
            context = LocalContext.current
            composable()
        }
    }

    /**
     * Runs the given action on the UI thread.
     *
     * This method is blocking until the action is complete.
     */
    fun <T> runOnUiThread(action: () -> T): T = composeTestRule.runOnUiThread(action)

    /**
     * Executes the given action in the same way as [runOnUiThread] but also makes sure Compose
     * is idle before executing it. This is great place for doing your assertions on shared
     * variables.
     *
     * This method is blocking until the action is complete.
     *
     * In case the main clock auto advancement is enabled (by default is) this will also keep
     * advancing the clock until it is idle (meaning there are no recompositions, animations, etc.
     * pending). If not, this will wait only for other idling resources.
     */
    fun <T> runOnIdle(action: () -> T): T = composeTestRule.runOnIdle(action)

    /**
     * Waits for compose to be idle.
     *
     * This is a blocking call. Returns only after compose is idle.
     *
     * In case the main clock auto advancement is enabled (by default is) this will also keep
     * advancing the clock until it is idle (meaning there are no recompositions, animations, etc.
     * pending). If not, this will wait only for other idling resources.
     *
     * Can crash in case there is a time out. This is not supposed to be handled as it
     * surfaces only in incorrect tests.
     */
    fun waitForIdle() = composeTestRule.waitForIdle()

    /**
     * Suspends until compose is idle. Compose is idle if there are no pending compositions, no
     * pending changes that could lead to another composition, and no pending draw calls.
     *
     * In case the main clock auto advancement is enabled (by default is) this will also keep
     * advancing the clock until it is idle (meaning there are no recompositions, animations, etc.
     * pending). If not, this will wait only for other idling resources.
     */
    suspend fun awaitIdle() = composeTestRule.waitForIdle()

    /**
     * Blocks until the given condition is satisfied.
     *
     * In case the main clock auto advancement is enabled (by default is), this will also keep
     * advancing the clock on a frame by frame basis and yield for other async work at the end of
     * each frame. If the advancement of the main clock is not enabled this will work as a
     * countdown latch without any other advancements.
     *
     * There is also [MainTestClock.advanceTimeUntil] which is faster as it does not yield back
     * the UI thread.
     *
     * This method should be used in cases where [MainTestClock.advanceTimeUntil]
     * is not enough.
     *
     * @param timeoutMillis The time after which this method throws an exception if the given
     * condition is not satisfied. This is the wall clock time not the main clock one.
     * @param condition Condition that must be satisfied in order for this method to successfully
     * finish.
     *
     * @throws ComposeTimeoutException If the condition is not satisfied after [timeoutMillis].
     */
    fun waitUntil(timeoutMillis: Long = 1_000, condition: () -> Boolean) {
        composeTestRule.waitUntil(timeoutMillis, condition)
    }

    /**
     * Registers an [IdlingResource] in this test.
     */
    fun registerIdlingResource(idlingResource: IdlingResource) {
        composeTestRule.registerIdlingResource(idlingResource)
    }

    /**
     * Unregisters an [IdlingResource] from this test.
     */
    fun unregisterIdlingResource(idlingResource: IdlingResource) {
        composeTestRule.unregisterIdlingResource(idlingResource)
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
    inner class MTextField(override val node: SemanticsNodeInteraction) :
        MBaseView<MTextField>(node),
        EditableActions, TextAssertions {
        constructor(function: ComposeContentTestRule.() -> SemanticsNodeInteraction) : this(
            composeTestRule.function()
        )
    }

    /**
     * View with BaseActions and TextActions
     */
    inner class MText(override val node: SemanticsNodeInteraction) : MBaseView<MText>(node),
        TextActions, TextAssertions {
        constructor(function: ComposeContentTestRule.() -> SemanticsNodeInteraction) : this(
            composeTestRule.function()
        )
    }

    /**
     * View with BaseActions and BaseAssertions
     */
    inner class MView(override val node: SemanticsNodeInteraction) : MBaseView<MView>(node),
        BaseActions, BaseAssertions {
        constructor(function: ComposeContentTestRule.() -> SemanticsNodeInteraction) : this(
            composeTestRule.function()
        )
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
