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
            }
        }

        desktopMain {
            dependencies {
                implementation(Deps.Squareup.SQLDelight.sqliteDriver)
            }
        }

        iosMain {
            dependencies {
                implementation(Deps.Squareup.SQLDelight.nativeDriver)
            }
        }
    }
}
