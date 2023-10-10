import org.jetbrains.kotlin.gradle.targets.js.dsl.ExperimentalWasmDsl

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

@OptIn(ExperimentalWasmDsl::class)
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

        val wasmMain by getting {
            dependencies {
                dependsOn(commonMain)
            }
        }
    }
}

android {
    namespace = "org.example.shared"
    compileSdk = 33
}