import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework

plugins {
    id("jisho.convention.kmp")
    id("com.rickclephas.kmp.nativecoroutines") version "0.13.0"
}

android.namespace = "in.surajsau.jisho.download"

kotlin {
    sourceSets {
        val isCI = System.getenv()["isCI"]?.toBoolean() ?: false
        val isCD = System.getenv()["isCD"]?.toBoolean() ?: false

        val targets = when {
            isCD -> listOf(iosArm64())
            isCI -> listOf(iosX64())
            else -> listOf(
                iosX64(),
                iosArm64(),
                iosSimulatorArm64()
            )
        }

        val xcframeworkName = "download"
        val xcframework = XCFramework(xcFrameworkName = xcframeworkName)

        targets.forEach {
            it.binaries.framework {
                baseName = xcframeworkName
                isStatic = true
                xcframework.add(this)
            }
        }

        val androidMain by getting {
            dependencies {
                api(project.dependencies.platform(libs.firebase.bom))
                api("com.google.firebase:firebase-storage-ktx")
            }
        }

    }
}