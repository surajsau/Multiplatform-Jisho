buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.10")
        classpath("com.android.tools.build:gradle:7.3.0-alpha03")
        classpath("com.squareup.sqldelight:gradle-plugin:1.5.3")
        classpath("com.google.gms:google-services:4.3.10")
        classpath("dev.icerock.moko:resources-generator:0.19.1")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}