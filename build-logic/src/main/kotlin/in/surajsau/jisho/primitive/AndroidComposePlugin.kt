package `in`.surajsau.jisho.primitive

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import java.io.File

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

private fun Project.buildComposeMetricsParameters(): List<String> {
    val metricParameters = mutableListOf<String>()
    val enableMetricsProvider = project.providers.gradleProperty("enableComposeCompilerMetrics")
    val enableMetrics = (enableMetricsProvider.orNull == "true")
    if (enableMetrics) {
        val metricsFolder = File(project.buildDir, "compose-metrics")
        metricParameters.add("-P")
        metricParameters.add(
            "plugin:androidx.compose.compiler.plugins.kotlin:metricsDestination=" + metricsFolder.absolutePath
        )
    }

    val enableReportsProvider = project.providers.gradleProperty("enableComposeCompilerReports")
    val enableReports = (enableReportsProvider.orNull == "true")
    if (enableReports) {
        val reportsFolder = File(project.buildDir, "compose-reports")
        metricParameters.add("-P")
        metricParameters.add(
            "plugin:androidx.compose.compiler.plugins.kotlin:reportsDestination=" + reportsFolder.absolutePath
        )
    }
    return metricParameters.toList()
}