plugins {
    id("multiplatform-setup")
    id("android-setup")
    kotlin("plugin.serialization") version Deps.JetBrains.Kotlin.VERSION
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(project(":common:database"))
                implementation(Deps.RusshWolf.settings)
                implementation(Deps.RusshWolf.settingsNoArg)
                implementation(Deps.Injection.Kodein.kodein)
                implementation(Deps.JetBrains.Kotlin.serialization)
            }
        }
    }
}
