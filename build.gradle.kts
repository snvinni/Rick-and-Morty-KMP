plugins {
    alias(libs.plugins.kotlin.multiplatform) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.compose.multiplatform.wasm) apply false
    kotlin("plugin.serialization") version "1.9.0" apply false
}

group = "org.example"
version = "1.0-DEV"

allprojects {
    repositories {
        google()
        mavenCentral()

        // Experimental Compose for Wasm
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        maven("https://maven.pkg.jetbrains.space/kotlin/p/wasm/experimental")
    }

    fixDuplicatedDependencies()
}

fun Project.fixDuplicatedDependencies() {
    configurations.all {
        val configuration = this

        configuration.resolutionStrategy.eachDependency {

            val isComposeGroup = requested.module.group.startsWith("org.jetbrains.compose")

            val isWasm = configuration.name.contains("wasm", true)
            val isJs = configuration.name.contains("js", true)
            val isComposeCompiler = requested.module.group.startsWith("org.jetbrains.compose.compiler")

            if (isComposeGroup && !isComposeCompiler && !isWasm && !isJs) {
                useVersion("1.4.0")
            }

            if (requested.module.name.startsWith("kotlin-stdlib")) {
                useVersion("1.9.0")
            }
        }
    }
}