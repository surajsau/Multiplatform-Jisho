plugins {
    id("jisho.convention.kmp")
    id("com.rickclephas.kmp.nativecoroutines") version "0.11.4-new-mm"
    id("org.jlleitschuh.gradle.ktlint") version "10.2.1"
}

android.namespace = "in.surajsau.jisho.shared"

kotlin {
    android()

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            linkerOpts.add("-lsqlite3")
        }
    }

    sourceSets {
        val resources = "0.19.1"
        val logging = "2.5.0"

        val commonMain by getting {
            dependencies {
                implementation("io.github.aakira:napier:$logging")
                implementation(project(":data"))
                implementation(project(":fake"))
            }
        }
        val androidMain by getting {
            dependencies {
                api("dev.icerock.moko:resources-compose:$resources")
                api("androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1")
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
