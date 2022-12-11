import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    @Suppress("DSL_SCOPE_VIOLATION")
    alias(libs.plugins.android.application) apply false
    @Suppress("DSL_SCOPE_VIOLATION")
    alias(libs.plugins.android.library) apply false
    @Suppress("DSL_SCOPE_VIOLATION")
    alias(libs.plugins.kotlin.gradle) apply false
    id("jisho.primitive.dependencygraph")
}

buildscript {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }

    dependencies {
        classpath(libs.android.gradlePlugin)
        classpath(libs.kotlin.gradlePlugin)
        classpath(libs.moko.resources.gradlePlugin)
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}
