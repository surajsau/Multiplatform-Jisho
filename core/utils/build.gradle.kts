plugins {
    id("jisho.convention.kmp")
    id("com.rickclephas.kmp.nativecoroutines") version "0.13.0"
    id("org.jlleitschuh.gradle.ktlint") version "10.2.1"
}

android.namespace = "in.surajsau.jisho.utils"

kotlin {
    explicitApi()

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(libs.napier.core)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(project.dependencies.platform(libs.compose.bom))
                implementation(libs.compose.runtime)
            }
        }
    }
}