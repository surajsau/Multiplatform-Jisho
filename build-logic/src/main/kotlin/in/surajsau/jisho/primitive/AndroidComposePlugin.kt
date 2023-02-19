package `in`.surajsau.jisho.primitive

import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

@Suppress("Unused")
class AndroidComposePlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            android {
                buildFeatures.compose = true
                composeOptions {
                    kotlinCompilerExtensionVersion = libs.findVersion("compose-compiler").get().toString()
                }

                kotlinOptions {
                    jvmTarget = JavaVersion.VERSION_17.toString()
                    freeCompilerArgs = freeCompilerArgs + buildComposeMetricsParameters()
                }
            }

            dependencies {
                add("kotlinCompilerPluginClasspath", libs.findLibrary("compose-compiler").get())

                bom(libs.findLibrary("compose-bom"))
                implementation(libs.findLibrary("compose-ui"))
                implementation(libs.findLibrary("compose-activity"))
                implementation(libs.findLibrary("compose-tooling"))
                implementation(libs.findLibrary("compose-foundation"))
                implementation(libs.findLibrary("compose-navigation"))
                implementation(libs.findLibrary("compose-material"))
                implementation(libs.findLibrary("compose-material3"))
                implementation(libs.findLibrary("androidx-runtime-compose"))
                implementation(libs.findLibrary("androidx-viewmodel-compose"))
            }
        }
    }
}