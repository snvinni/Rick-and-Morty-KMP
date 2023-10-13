import org.jetbrains.compose.ExperimentalComposeLibrary
import org.jetbrains.kotlin.gradle.targets.js.dsl.ExperimentalWasmDsl

group = "com.example.shared"
version = "1.0-SNAPSHOT"

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.library)
    alias(libs.plugins.compose.multiplatform.wasm)
    kotlin("plugin.serialization")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
        vendor.set(JvmVendorSpec.ORACLE)
    }
}

@OptIn(ExperimentalComposeLibrary::class, ExperimentalWasmDsl::class)
kotlin {

    androidTarget()

    jvm("desktop")

    wasm {
        browser()
    }

    sourceSets {
        val commonMain by getting {
            dependencies {

                // Compose
                api(compose.runtime)
                api(compose.foundation)
                api(compose.ui)
                api(compose.material)
                api(compose.materialIconsExtended)
                api(compose.components.resources)

                // Serialization
                api(libs.kotlinx.serialization.json)

                // Immutable collections
                api("org.jetbrains.kotlinx:kotlinx-collections-immutable:0.4-wasm0")

                // Ktor
                api(libs.ktor.core.wasm)
            }
        }

        val wasmMain by getting

        val androidMain by getting {
            dependencies {

                // Compose
                api("androidx.compose.ui:ui-tooling-preview:1.4.0")

                // For Compose
                api("androidx.lifecycle:lifecycle-viewmodel-compose:2.4.0")

                // Ktor
                api(libs.ktor.http)

                // Lifecycle
                api(libs.lifecycle.viewmodel.ktx)
            }
        }

        val desktopMain by getting {
            dependencies {

                // For Dispatchers.Main
                api("org.jetbrains.kotlinx:kotlinx-coroutines-swing:1.6.4")

                // Ktor
                api(libs.ktor.http)

                // slf4j
                api("org.slf4j:slf4j-simple:1.7.32")

                // Compose
                api(compose.desktop.common)
            }
        }
    }
}

android {
    namespace = "org.example.shared"
    compileSdk = 33

    // sourceSets["main"].resources.srcDirs("src/commonMain/resources")
}