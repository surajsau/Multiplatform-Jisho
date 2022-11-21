plugins {
    id("jisho.convention.kmp")
    id("com.rickclephas.kmp.nativecoroutines") version "0.11.4-new-mm"
    id("org.jlleitschuh.gradle.ktlint") version "10.2.1"
}

android.namespace = "in.surajsau.jisho.viewmodel"

kotlin {
    explicitApi()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(projects.core.data)
                implementation(projects.core.model)
                implementation(projects.core.utils)
            }
        }
        val androidMain by getting {
            dependencies {
                api(libs.androidx.viewmodel)
            }
        }
    }
}