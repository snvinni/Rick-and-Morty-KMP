import org.jetbrains.kotlin.gradle.targets.js.dsl.ExperimentalWasmDsl

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose.multiplatform.wasm)
}

group = "com.example.web"
version = "1.0-SNAPSHOT"

@OptIn(ExperimentalWasmDsl::class)
kotlin {

    wasm {

        moduleName = "web"

        binaries.executable()

        browser()
    }

    js(IR) {

        binaries.executable()

        browser()
    }

    sourceSets {

        val commonMain by getting {
            dependencies {
                api(project(":shared"))
            }
        }

        val wasmMain by getting

        val jsMain by getting
    }
}

compose.experimental {
    web.application {}
}

compose {
    kotlinCompilerPlugin.set("1.4.0-dev-wasm09")
    kotlinCompilerPluginArgs.add("suppressKotlinVersionCompatibilityCheck=1.9.0")
}