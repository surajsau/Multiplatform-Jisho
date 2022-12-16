plugins {
    id("jisho.convention.kmp")
    id("jisho.primitive.sqldelight")
    id("com.rickclephas.kmp.nativecoroutines") version "0.13.0"
    id("org.jlleitschuh.gradle.ktlint") version "10.2.1"
}

android.namespace = "in.surajsau.jisho.data"

kotlin {
    explicitApi()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(projects.core.model)
                implementation(projects.core.utils)
            }
        }
    }
}

ktlint {
    verbose.set(true)
    outputToConsole.set(true)
    enableExperimentalRules.set(true)
    disabledRules.set(setOf("final-newline", "filename"))
    filter {
        exclude("**/generated/**")
        include("**/kotlin/**")
    }
}