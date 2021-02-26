object Deps {

    object JetBrains {
        object Kotlin {
            // __KOTLIN_COMPOSE_VERSION__
            const val VERSION = "1.4.30"
            const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$VERSION"
            const val testCommon = "org.jetbrains.kotlin:kotlin-test-common:$VERSION"
            const val testJunit = "org.jetbrains.kotlin:kotlin-test-junit:$VERSION"
            const val testAnnotationsCommon = "org.jetbrains.kotlin:kotlin-test-annotations-common:$VERSION"
            const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.2"
            const val serializationPlugin = "org.jetbrains.kotlin.plugin.serialization:$VERSION"
            const val serialization = "org.jetbrains.kotlinx:kotlinx-serialization-json:1.1.0"
        }

        object Compose {
            private const val VERSION = "0.3.0-build152"
            const val gradlePlugin = "org.jetbrains.compose:compose-gradle-plugin:$VERSION"
        }
    }

    object Android {
        object Tools {
            object Build {
                const val gradlePlugin = "com.android.tools.build:gradle:4.0.1"
            }
        }
    }

    object AndroidX {
        object AppCompat {
            const val appCompat = "androidx.appcompat:appcompat:1.1.0"
        }

        object Activity {
            const val activityCompose = "androidx.activity:activity-compose:1.3.0-alpha02"
        }
    }

    object ArkIvanov {
        object MVIKotlin {
            private const val VERSION = "2.0.1"
            const val rx = "com.arkivanov.mvikotlin:rx:$VERSION"
            const val mvikotlin = "com.arkivanov.mvikotlin:mvikotlin:$VERSION"
            const val mvikotlinMain = "com.arkivanov.mvikotlin:mvikotlin-main:$VERSION"
            const val mvikotlinMainIosX64 = "com.arkivanov.mvikotlin:mvikotlin-main-iosx64:$VERSION"
            const val mvikotlinMainIosArm64 = "com.arkivanov.mvikotlin:mvikotlin-main-iosarm64:$VERSION"
            const val mvikotlinLogging = "com.arkivanov.mvikotlin:mvikotlin-logging:$VERSION"
            const val mvikotlinTimeTravel = "com.arkivanov.mvikotlin:mvikotlin-timetravel:$VERSION"
            const val mvikotlinExtensionsReaktive = "com.arkivanov.mvikotlin:mvikotlin-extensions-reaktive:$VERSION"
        }

        object Decompose {
            private const val VERSION = "0.1.8"
            const val decompose = "com.arkivanov.decompose:decompose:$VERSION"
            const val decomposeIosX64 = "com.arkivanov.decompose:decompose-iosx64:$VERSION"
            const val decomposeIosArm64 = "com.arkivanov.decompose:decompose-iosarm64:$VERSION"
            const val extensionsCompose = "com.arkivanov.decompose:extensions-compose-jetbrains:$VERSION"
        }
    }

    object Badoo {
        object Reaktive {
            private const val VERSION = "1.1.20"
            const val reaktive = "com.badoo.reaktive:reaktive:$VERSION"
            const val reaktiveTesting = "com.badoo.reaktive:reaktive-testing:$VERSION"
            const val utils = "com.badoo.reaktive:utils:$VERSION"
            const val coroutinesInterop = "com.badoo.reaktive:coroutines-interop:$VERSION"
        }
    }

    object Squareup {
        object SQLDelight {
            private const val VERSION = "1.4.4"

            const val gradlePlugin = "com.squareup.sqldelight:gradle-plugin:$VERSION"
            const val androidDriver = "com.squareup.sqldelight:android-driver:$VERSION"
            const val sqliteDriver = "com.squareup.sqldelight:sqlite-driver:$VERSION"
            const val nativeDriver = "com.squareup.sqldelight:native-driver:$VERSION"
        }
    }

    object RusshWolf {
        const val settings = "com.russhwolf:multiplatform-settings:0.7.3"
        const val settingsNoArg = "com.russhwolf:multiplatform-settings-no-arg:0.7.3"
    }

    object Encryption {
        object SqlCipher {
            const val sqlCipher = "net.zetetic:android-database-sqlcipher:4.4.2"
            const val sqlCipherJvm = "io.github.willena:sqlite-jdbc:3.33.0.1"
        }
        object Simboise {
            const val encryption = "com.github.simbiose:Encryption:2.0.1"
        }
    }

    object Injection {
        object PopKorn {
            private const val VERSION = "2.1.0"
            const val popkorn = "cc.popkorn:popkorn:$VERSION"
            const val popkornCompiler = "cc.popkorn:popkorn-compiler:$VERSION"
        }
        object Kodein {
            private const val VERSION = "7.3.0"
            const val kodein = "org.kodein.di:kodein-di:$VERSION"
        }
    }
}
