plugins {
    kotlin("multiplatform") version "1.9.0" apply false
    kotlin("android") version "1.9.0" apply false
    id("com.android.application") version "8.1.0" apply false
    id("com.android.library") version "8.1.0" apply false
    id("org.jetbrains.compose") version "1.4.0-dev-wasm09" apply false
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