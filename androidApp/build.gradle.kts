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
    val firebase = "29.3.1"

    implementation(project(":shared"))

    implementation(libs.compose.ui)
    implementation(libs.compose.activity)
    implementation(libs.compose.tooling)
    implementation(libs.compose.foundation)
    implementation(libs.compose.navigation)

    implementation(libs.androidx.viewmodel.compose)

    implementation(libs.material.compose.core)
    implementation(libs.material.compose.theme)
    implementation(libs.material.compose.icons.core)
    implementation(libs.material.compose.icons.extended)

    implementation(libs.accompanist.coil)
    implementation(libs.accompanist.swiperefresh)

    implementation(libs.koin.android)

    implementation(platform("com.google.firebase:firebase-bom:$firebase"))
    implementation("com.google.firebase:firebase-analytics-ktx")
    implementation("com.google.firebase:firebase-storage-ktx")
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

kotlin.sourceSets.all {
    languageSettings.optIn("kotlin.RequiresOptIn")
}
