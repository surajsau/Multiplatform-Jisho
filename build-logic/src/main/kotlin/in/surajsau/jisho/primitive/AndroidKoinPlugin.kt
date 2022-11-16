package `in`.surajsau.jisho.primitive

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

@Suppress("Unused")
class AndroidKoinPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            dependencies {
                implementation(libs.findLibrary("koin-core"))
                implementation(libs.findLibrary("koin-android"))
                implementation(libs.findLibrary("koin-compose"))
            }
        }
    }

}