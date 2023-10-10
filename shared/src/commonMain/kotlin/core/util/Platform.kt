package core.util

enum class Platform {
    ANDROID,
    WASM
}

expect fun getPlatform(): Platform