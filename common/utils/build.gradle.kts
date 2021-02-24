plugins {
    id("multiplatform-setup")
    id("android-setup")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(Deps.RusshWolf.settings)
                implementation(Deps.Injection.popkorn)
                kapt {
                    annotationProcessor(Deps.Injection.popkornCompiler)
                }
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
