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
}

dependencies {
    val firebase = "29.3.1"

    implementation(project(":shared"))

    implementation(platform("com.google.firebase:firebase-bom:$firebase"))
    implementation("com.google.firebase:firebase-analytics-ktx")
    implementation("com.google.firebase:firebase-storage-ktx")

    implementation(libs.compose.icons.core)
    implementation(libs.compose.icons.extended)
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
