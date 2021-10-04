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

import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions

plugins {
    id("com.android.application")
    id("kotlin-android")
}

/*
 * For now the definition is like this to avoid having all the script marked red. For some reason
 * doing just android {} without the interface implementation results in the IDE marking all the
 * block as an error, so for now we do it this way, even if it's a bit verbose <_<
 *
 * Recheck both the "android" and "kotlinOptions" block when we update the Android Gradle plug-in.
 */

android(object : Action<BaseAppModuleExtension> {
    override fun execute(t: BaseAppModuleExtension) {
        with(t) {
            compileSdk = 31

            defaultConfig {
                applicationId = "com.hyperdevs.morsa"
                minSdk = 21
                targetSdk = 31
                versionCode = 1
                versionName = "1.0"

                testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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

    implementation("androidx.appcompat:appcompat:1.3.1")
    implementation("com.google.android.material:material:1.4.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.1")
    testImplementation("junit:junit:4.13.2")

    androidTestImplementation(project(":lib"))
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}