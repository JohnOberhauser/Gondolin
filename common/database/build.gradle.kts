plugins {
    id("multiplatform-setup")
    id("android-setup")
    id("com.squareup.sqldelight")
}

sqldelight {
    database("CredentialsDatabase") {
        packageName = "ober.gondolin.common.database"
    }
}

kotlin {
    sourceSets {

        commonMain {
            dependencies {
                implementation(Deps.Injection.popkorn)
                kapt {
                    annotationProcessor(Deps.Injection.popkornCompiler)
                }
            }
        }

        androidMain {
            dependencies {
                implementation(Deps.Squareup.SQLDelight.androidDriver)
                implementation(Deps.Squareup.SQLDelight.sqliteDriver)
                implementation(Deps.Encryption.SqlCipher.sqlCipher)
            }
        }

        desktopMain {
            dependencies {
                implementation(Deps.Squareup.SQLDelight.sqliteDriver)
                implementation(Deps.Encryption.SqlCipher.sqlCipherJvm)
            }
        }

        iosMain {
            dependencies {
                implementation(Deps.Squareup.SQLDelight.nativeDriver)
            }
        }
    }
}
