plugins {
    id("multiplatform-setup")
    id("android-setup")
}

kotlin {
    sourceSets {
        named("commonMain") {
            dependencies {
                implementation(Deps.JetBrains.Kotlin.coroutines)
                implementation(Deps.Injection.Kodein.kodein)
            }
        }
    }
}
