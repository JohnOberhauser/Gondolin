plugins {
    id("multiplatform-compose-setup")
    id("android-setup")
}

kotlin {
    sourceSets {
        named("commonMain") {
            dependencies {
                implementation(project(":common:viewmodel"))
                implementation(project(":common:navigation"))
                implementation(Deps.Injection.Kodein.kodein)
            }
        }
    }
}
