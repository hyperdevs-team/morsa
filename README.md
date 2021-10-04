# Morsa: Jetpack Compose Testing Framework
[![Release](https://jitpack.io/v/hyperdevs-team/morsa.svg)](https://jitpack.io/#hyperdevs-team/morsa)

Test library to ease UI testing with Jetpack Compose

## Purpose
🚧🚧🚧🚧🚧

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
Morsa is verbose and easy to use, we made use of testing tags, text values or content descriptions to find our views
inside a Jetpack Compose scope making use of the `ComposeContentTestRule` class.

First of all we need to declare our `MorsaScreen` targeting our desired views. These can be done using 3 methods:  
- `withTag` which will search in your Compose view hierarchy for a component tagged with `Modifier.testTag(value)`.
- `withText` which will search in your Compose view hierarchy for a component with the given text.
- `withContentDescription` which will search in your Compose view hierarchy for a component tagged with `Modifier.contentDescription(value)`.

```kotlin
class LoginMorsaScreen(testRule: ComposeContentTestRule) : MorsaScreen<LoginMorsaScreen>(testRule) {
    val usernameTextField = MTextField { withTag(USERNAME_TEXT_FIELD_DEFAULT_TAG) }
    val passwordTextField = MTextField { withTag(PASSWORD_TEXT_FIELD_DEFAULT_TAG) }
    val loginButton = MText { withTag(LOGIN_SCREEN_LOGIN_BUTTON_TAG) }
    val errorView = MView { withTag(LOGIN_SCREEN_ERROR_BOX_TAG) }
}
```

After declare our view we can start doing out testing:
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

## Authors & Collaborators
* **[Francisco García Sierra](https://github.com/FrangSierra)** - *Initial work, Maintainer*
* **[Adrián García](https://github.com/adriangl)** - *Maintainer*

## Acknowledgements
The ideas in this repository were based in the awesome work of the based on [Kakao](https://github.com/KakaoCup/Kakao)
library that we used extensively to do UI Testing with the traditional `View` system in Android. Go check them out!

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
