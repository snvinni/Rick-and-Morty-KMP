package core

enum class Platform {
    ANDROID,
    WASM
}

expect fun getPlatform(): Platform