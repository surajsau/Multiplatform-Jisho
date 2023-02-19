plugins {
    kotlin("kapt")
    id("jisho.primitive.android.application")
    id("jisho.primitive.android.kotlin")
    id("jisho.primitive.compose")
    id("jisho.primitive.firebase")
    id("jisho.primitive.koin.android")
    id("org.jlleitschuh.gradle.ktlint") version "10.2.1"
}

android {
    compileSdk = 33
    defaultConfig {
        applicationId = "in.surajsau.jisho.android"
        minSdk = 26
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }
    buildFeatures {
        compose = true
    }
    namespace = "in.surajsau.jisho.android"
}

dependencies {
    implementation(projects.shared)

    implementation(projects.core.model)
    implementation(projects.core.utils)
    implementation(projects.core.ui)
    implementation(projects.core.preference)
    implementation(projects.core.app)
    implementation(projects.core.download)

    implementation(projects.feature.home)
    implementation(projects.feature.download)
    implementation(projects.feature.jlpt)
    implementation(projects.feature.kanji)
    implementation(projects.feature.details)
    implementation(projects.feature.sentence)

    implementation(platform(libs.firebase.bom))
    implementation("com.google.firebase:firebase-analytics-ktx")

    implementation(libs.compose.icons.core)
    implementation(libs.compose.icons.extended)
    implementation(libs.accompanist.navigation.animation)
}

ktlint {
    verbose.set(true)
    outputToConsole.set(true)
    enableExperimentalRules.set(true)
    android.set(true)
    disabledRules.set(setOf("final-newline", "import-ordering"))
    filter {
        exclude("**/generated/**")
        include("**/kotlin/**")
    }
}

kapt {
    correctErrorTypes = true
}
