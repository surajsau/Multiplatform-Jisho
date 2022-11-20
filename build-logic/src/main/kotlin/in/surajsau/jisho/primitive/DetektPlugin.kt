package `in`.surajsau.jisho.primitive

import io.gitlab.arturbosch.detekt.Detekt
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.register

@Suppress("unused")
class DetektPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("io.gitlab.arturbosch.detekt")
            }
            dependencies {

            }
            dependencies {
                detektPlugins(libs.findLibrary("detekt-twitterComposeRules"))
            }

            tasks.register<Detekt>("composeLint") {
                configure()
            }
        }
    }
}

private fun Detekt.configure() {
    parallel = true

    source = project.files("./").asFileTree
    include("**/*.kt")
    include("**/*.kts")
    exclude("**/resources/**")
    exclude("**/build/**")

    config.setFrom(project.rootDir.resolve("detekt.yml"))

    reports {
        txt.required.set(true)
    }
}