# Morsa: Jetpack Compose UI Testing Framework
[![Release](https://jitpack.io/v/hyperdevs-team/morsa.svg)](https://jitpack.io/#hyperdevs-team/morsa)

Test library to ease UI testing with Jetpack Compose

## Purpose
This library aims to add some useful wrappers and abstractions over common Espresso functions used while testing UI in your Android apps so you can write UI tests as fast as you create Compose screens. The library will also help you write more readable tests, thus improving their maintainability.

## Why `morsa`?
🚧🚧🚧🚧🚧

## Setting Up
In your main `build.gradle`, add [jitpack.io](https://jitpack.io/) repository in the `buildscript` block and include the library as a dependency:

<details open><summary>Groovy</summary>

```groovy
buildscript {
    repositories { 
        maven { url "https://jitpack.io" }
    }
    dependencies {
        classpath "com.github.hyperdevs-team:morsa:0.0.2"
    }
}
```

</details>

<details><summary>Kotlin</summary>

```kotlin
buildscript {
    repositories { 
        maven("https://jitpack.io")
    }
    dependencies {
        classpath("com.github.hyperdevs-team:morsa:0.0.2")
    }
}
```

</details>

## How to use
🚧🚧🚧🚧🚧
Morsa is verbose and easy to use. The library exposes function to find components based on testing tags, text values or content descriptions inside a Jetpack Compose scope making use of the `ComposeContentTestRule` class.

First of all, you'll need to mark your components with either:
* `Modifier.testTag(tag)`
* `Modifier.contentDescription(contentDescription)`
Or just be sure that you can find them with a given text so Morsa can find them.

Then, in our test class we need to declare a `MorsaScreen` targeting the components that compose our screen. The components can be identified using the following functions:  
* `withTag(tag)` will search in your Compose component hierarchy for a component tagged with `Modifier.testTag(tag)`.
* `withText(text)` will search in your Compose component hierarchy for a component with the given text.
* `withContentDescription(contentDescription)` will search in your Compose component hierarchy for a component marked with `Modifier.contentDescription(contentDescription)`.

```kotlin
class LoginMorsaScreen(testRule: ComposeContentTestRule) : MorsaScreen<LoginMorsaScreen>(testRule) {
    val usernameTextField = MTextField { withTag(USERNAME_TEXT_FIELD_DEFAULT_TAG) }
    val passwordTextField = MTextField { withTag(PASSWORD_TEXT_FIELD_DEFAULT_TAG) }
    val loginButton = MText { withTag(LOGIN_SCREEN_LOGIN_BUTTON_TAG) }
    val errorView = MView { withTag(LOGIN_SCREEN_ERROR_BOX_TAG) }
}
```

When your screen definition is done, you can start doing tests with it:
```kotlin

class LoginContentTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    val screen = LoginMorsaScreen(composeTestRule)

    @Test
    fun login_shows_error_box_on_error() {
        screen {
            //Set your compose view, this can be a whole screen or a single component
            setContent {
                MyMaterialTheme {
                    LoginContent()
                }
            }
            //These values should trigger an error on our view showing our errorView component
            usernameTextField { typeText("username@gmail.com") }
            passwordTextField { typeText("password123") }

            loginButton.click()
            //Assert over the view
            errorView {
                exist()
                isDisplayed()
            }
        }
    }
}
```

## Acknowledgements
The ideas in this library were based on the awesome [Kakao](https://github.com/KakaoCup/Kakao)
library that we used extensively to do UI Testing with the traditional `View` system in Android. Go check them out!

## Authors & Collaborators
* **[Francisco García Sierra](https://github.com/FrangSierra)** - *Initial work, Maintainer*
* **[Adrián García](https://github.com/adriangl)** - *Maintainer*

## License
This project is licensed under the Apache Software License, Version 2.0.
```
   Copyright 2021 HyperDevs

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
```
