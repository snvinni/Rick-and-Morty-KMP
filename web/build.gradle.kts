import org.jetbrains.kotlin.gradle.targets.js.dsl.ExperimentalWasmDsl

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose.multiplatform.wasm)
}

@OptIn(ExperimentalWasmDsl::class)
kotlin {

    wasm {

        // Used in load.mjs
        moduleName = "webAppModule"

        binaries.executable()

        browser()
    }

    sourceSets {
        val wasmMain by getting  {
            dependencies {
                implementation(project(":shared"))
            }
        }
    }
}

compose.experimental {
    web.application {}
}