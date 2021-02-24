plugins {
    id("multiplatform-setup")
    id("android-setup")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(Deps.RusshWolf.settings)
                implementation(Deps.RusshWolf.settingsNoArg)
                implementation(Deps.Injection.Kodein.kodein)
            }
        }

        androidMain {
            dependencies {
                implementation(Deps.Encryption.Simboise.encryption)
            }
        }

        desktopMain {
            dependencies {
                implementation(Deps.Encryption.Simboise.encryption)
            }
        }
    }
}
