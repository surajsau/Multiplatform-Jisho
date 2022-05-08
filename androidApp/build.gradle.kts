plugins {
    id("com.android.application")
    kotlin("android")
    id("com.google.gms.google-services")
    id("org.jlleitschuh.gradle.ktlint") version "10.2.1"
}

android {
    compileSdk = 32
    defaultConfig {
        applicationId = "in.surajsau.jisho.android"
        minSdk = 24
        targetSdk = 32
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }

    buildFeatures {
        compose = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.2.0-alpha07"
    }
}

dependencies {
    val compose = "1.2.0-alpha07"
    val firebase = "29.3.1"
    val accompanist = "0.13.0"
    val navigation = "2.4.2"
    val koin = "3.1.6"

    implementation(project(":shared"))

    implementation("androidx.compose.ui:ui:$compose")
    implementation("androidx.activity:activity-compose:1.4.0")
    implementation("androidx.compose.ui:ui-tooling:$compose")
    implementation("androidx.compose.foundation:foundation:$compose")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.4.1")
    implementation("com.google.android.material:compose-theme-adapter:1.1.7")
    implementation("androidx.compose.material:material:$compose")
    implementation("androidx.compose.material:material-icons-core:$compose")
    implementation("androidx.compose.material:material-icons-extended:$compose")
    implementation("com.google.accompanist:accompanist-coil:$accompanist")
    implementation("com.google.accompanist:accompanist-swiperefresh:$accompanist")
    implementation("androidx.navigation:navigation-compose:$navigation")

    implementation("io.insert-koin:koin-androidx-compose:$koin")

    implementation(platform("com.google.firebase:firebase-bom:$firebase"))
    implementation("com.google.firebase:firebase-analytics-ktx")
    implementation("com.google.firebase:firebase-storage-ktx")
}

ktlint {
    verbose.set(true)
    outputToConsole.set(true)
    enableExperimentalRules.set(true)
    android.set(true)
    disabledRules.set(setOf("final-newline"))
    filter {
        exclude("**/generated/**")
        include("**/kotlin/**")
    }
}

kotlin.sourceSets.all {
    languageSettings.optIn("kotlin.RequiresOptIn")
}