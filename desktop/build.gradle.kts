import org.jetbrains.compose.desktop.application.dsl.TargetFormat

group = "com.example.desktop"
version = "1.0-SNAPSHOT"

plugins {
    kotlin("jvm")
    id("org.jetbrains.compose")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
        vendor.set(JvmVendorSpec.ORACLE)
    }
}

dependencies {
    implementation(project(":shared"))
    implementation(compose.desktop.currentOs)
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)

            packageName = "desktop"
            packageVersion = "1.0.0"
        }
    }
}
