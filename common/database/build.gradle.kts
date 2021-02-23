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

        androidMain {
            dependencies {
                implementation(Deps.Squareup.SQLDelight.androidDriver)
                implementation(Deps.Squareup.SQLDelight.sqliteDriver)
                implementation(Deps.SqlCipher.sqlCipher)
            }
        }

        desktopMain {
            dependencies {
                implementation(Deps.Squareup.SQLDelight.sqliteDriver)
                implementation(Deps.SqlCipher.sqlCipherJvm)
            }
        }

        iosMain {
            dependencies {
                implementation(Deps.Squareup.SQLDelight.nativeDriver)
            }
        }
    }
}
