import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework

plugins {
    id("jisho.convention.kmp")
    id("com.rickclephas.kmp.nativecoroutines") version "0.11.4-new-mm"
    id("org.jlleitschuh.gradle.ktlint") version "10.2.1"
}

android.namespace = "in.surajsau.jisho.shared"

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

        val xcframeworkName = "shared"
        val xcframework = XCFramework(xcFrameworkName = xcframeworkName)

        val exportProjects = listOf(
            projects.core.model,
            projects.core.viewmodel,
            projects.fake,
            projects.core.utils,
            projects.core.preference
        )

        targets.forEach {
            it.binaries.framework {
                baseName = xcframeworkName
                isStatic = true
                xcframework.add(this)

                exportProjects.forEach { project ->
                    export(project)
                }
            }
        }

        val commonMain by getting {
            dependencies {
                exportProjects.forEach { project ->
                    api(project)
                }
            }
        }

        val androidMain by getting {
            dependencies {
                api(libs.moko.resources.compose)
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
