package core.util

enum class Platform {
    ANDROID,
    WASM,
    DESKTOP
}

expect fun getPlatform(): Platform