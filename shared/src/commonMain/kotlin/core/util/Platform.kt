package core.util

enum class Platform {
    ANDROID,
    WASM,
    JS,
    DESKTOP
}

expect fun getPlatform(): Platform