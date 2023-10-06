import org.jetbrains.kotlin.gradle.targets.js.dsl.ExperimentalWasmDsl

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("org.jetbrains.compose")
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

                implementation("io.ktor:ktor-client-core:2.3.3-wasm0")
            }
        }

        val androidMain by getting {
            dependencies {

                // view model
                api("io.ktor:ktor-client-okhttp:2.3.3")
                api("androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.0")
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