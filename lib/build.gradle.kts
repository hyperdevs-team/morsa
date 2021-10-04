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

import com.android.build.gradle.LibraryExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions

plugins {
    id("com.android.library")
    id("kotlin-android")
}

/*
 * For now the definition is like this to avoid having all the script marked red. For some reason
 * doing just android {} without the interface implementation results in the IDE marking all the
 * block as an error, so for now we do it this way, even if it's a bit verbose <_<
 *
 * Recheck the "android" and "kotlinOptions" blocks when we update the Android Gradle plug-in.
 */
android(object : Action<LibraryExtension> {
    override fun execute(t: LibraryExtension) {
        with(t) {
            compileSdk = 31

            defaultConfig {
                minSdk = 21
                targetSdk = 31

                testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                consumerProguardFiles.add(file("consumer-rules.pro"))
            }

            buildTypes {
                getByName("release") {
                    isMinifyEnabled = false
                    proguardFiles(
                        getDefaultProguardFile("proguard-android-optimize.txt"),
                        "proguard-rules.pro"
                    )
                }
            }

            buildFeatures {
                compose = true
            }

            composeOptions {
                kotlinCompilerExtensionVersion = "1.0.2"
            }

            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_1_8
                targetCompatibility = JavaVersion.VERSION_1_8
            }

            kotlinOptions(object : Action<KotlinJvmOptions> {
                override fun execute(t: KotlinJvmOptions) {
                    with(t) {
                        jvmTarget = "1.8"
                    }
                }
            })

            sourceSets {
                // Add kotlin folders as possible source folders
                all {
                    java.srcDirs("src/${name}/kotlin")
                }
            }
        }
    }
})

dependencies {
    // Test rules and transitive dependencies:
    implementation("androidx.compose.ui:ui-test-junit4:1.0.2")
    // Needed for createComposeRule, but not createAndroidComposeRule:
    implementation("androidx.compose.ui:ui-test-manifest:1.0.2")
}