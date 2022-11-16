package `in`.surajsau.jisho.primitive

import org.gradle.api.Plugin
import org.gradle.api.Project

@Suppress("Unused")
class AndroidKotlinPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("org.jetbrains.kotlin.android")
            }

            android {
                kotlinOptions {
                    allWarningsAsErrors = properties["warningsAsErrors"] as? Boolean ?: false

                    freeCompilerArgs = freeCompilerArgs + listOf(
                        "-opt-in=kotlin.RequiresOptIn",
                        "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
                        "-Xcontext-receivers"
                    )

                    jvmTarget = org.gradle.api.JavaVersion.VERSION_1_8.toString()
                }
            }
        }
    }

}