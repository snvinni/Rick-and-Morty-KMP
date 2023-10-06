pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()

        // Experimental Compose for Wasm
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        maven("https://maven.pkg.jetbrains.space/kotlin/p/wasm/experimental")
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}

rootProject.name = "KMP-WasmAndAndroid"

include(":shared")
include(":android")
include(":web")
