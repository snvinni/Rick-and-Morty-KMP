import org.jetbrains.compose.ExperimentalComposeLibrary

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

@OptIn(ExperimentalComposeLibrary::class)
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

                // Ktor
                api(libs.ktor.core.wasm)
            }
        }

        val wasmMain by getting

        val androidMain by getting {
            dependencies {
                // Ktor
                api(libs.ktor.http)

                // Lifecycle
                api(libs.lifecycle.viewmodel.ktx)
            }
        }

        val desktopMain by getting {
            dependencies {
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