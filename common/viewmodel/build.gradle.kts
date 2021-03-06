import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    id("multiplatform-setup")
    id("android-setup")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(project(":common:utils"))
                implementation(project(":common:navigation"))
                implementation(Deps.JetBrains.Kotlin.coroutines)
                implementation(Deps.Injection.Kodein.kodein)
            }
        }
    }

    targets.getByName<KotlinNativeTarget>("iosX64").compilations.forEach {
        it.kotlinOptions.freeCompilerArgs += arrayOf("-linker-options", "-lsqlite3")
    }
}
