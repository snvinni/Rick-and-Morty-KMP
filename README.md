#  [Template] KMP Wasm and Android with Compose

Template of a Kotlin Multiplatform Project with Wasm target, Android target and Compose Multiplatform (working).

## Configure your browser (Wasm

Enabled webassembly garbage collection in your browser for wasm target.

**Chrome (110 or later):**

1. Go to `chrome://flags/#enable-webassembly-garbage-collection` in your browser.
2. Enable WebAssembly Garbage Collection.
3. Relaunch your browser.

## Configure Android SDK

Create a file `local.properties` in the root of the project and add the following line:`sdk.dir=C\:\\Users\\your-user\\AppData\\Local\\Android\\Sdk` or define the environment variable `ANDROID_HOME` with the path of your Android SDK.

## Run

**Wasm**
Run the following Gradle command in the terminal: `./gradlew :web:wasmBrowserRun`

**Android**
Run the following Gradle command in the terminal: `./gradlew :android:installDebug`

## Starting template
- [KMP Empty Project](https://github.com/Irineu333/KMP-Empty-Project)

## Sources
- [Configure a Gradle project | Kotlin](https://kotlinlang.org/docs/gradle-configure-project.html)
- [Understand Multiplatform project structure | Kotlin](https://kotlinlang.org/docs/multiplatform-discover-project.html)

## Samples
- [Kotlin Multiplatform for wasm samples](https://github.com/Kotlin/kotlin-wasm-examples)