plugins {
    `kotlin-dsl`
}

allprojects {
    repositories {
        //mavenCentral()
        //maven("https://kotlin.bintray.com/kotlinx")
        google()
        jcenter()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        maven("https://jitpack.io")
    }
}
