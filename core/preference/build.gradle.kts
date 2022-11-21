plugins {
    id("jisho.convention.kmp")
    id("com.rickclephas.kmp.nativecoroutines") version "0.11.4-new-mm"
    id("org.jlleitschuh.gradle.ktlint") version "10.2.1"
}

android.namespace = "in.surajsau.jisho.preference"

kotlin {
    explicitApi()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.multiplatform.settings)
                implementation(libs.multiplatform.settings.coroutines)
                implementation(projects.core.model)
            }
        }

        val androidMain by getting {
            dependencies {
                implementation(libs.multiplatform.settings.datastore)
                implementation(libs.androidx.datastore.core)
                implementation(libs.androidx.datastore.preference)
            }
        }
    }
}