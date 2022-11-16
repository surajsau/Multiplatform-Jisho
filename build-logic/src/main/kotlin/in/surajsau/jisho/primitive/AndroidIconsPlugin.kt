package `in`.surajsau.jisho.primitive

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

@Suppress("Unused")
class AndroidIconsPlugin: Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            dependencies {
                implementation(libs.findLibrary("compose-icons-core"))
                implementation(libs.findLibrary("compose-icons-extended"))
            }
        }
    }

}