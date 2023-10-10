import org.jetbrains.compose.ExperimentalComposeLibrary

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.library)
    alias(libs.plugins.compose.multiplatform.wasm)
    kotlin("plugin.serialization")
}

group = "org.example"
version = "1.0-DEV"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
        vendor.set(JvmVendorSpec.ORACLE)
    }
}

@OptIn(ExperimentalComposeLibrary::class)
kotlin {

    androidTarget()

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

                api(libs.kotlinx.serialization.json)
                api(libs.ktor.core.wasm)
            }
        }

        val androidMain by getting {
            dependencies {

                // view model
                api(libs.ktor.http)
                api(libs.lifecycle.viewmodel.ktx)
            }
        }

        val wasmMain by getting
    }
}

android {
    namespace = "org.example.shared"
    compileSdk = 33

    // sourceSets["main"].resources.srcDirs("src/commonMain/resources")
}