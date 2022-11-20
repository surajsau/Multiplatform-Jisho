package `in`.surajsau.jisho.convention

import `in`.surajsau.jisho.primitive.implementation
import `in`.surajsau.jisho.primitive.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

@Suppress("unused")
class AndroidFeaturePlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("jisho.primitive.android.module")
                apply("jisho.primitive.android.kotlin")
                apply("jisho.primitive.compose")
                apply("jisho.primitive.koin.android")
                apply("jisho.primitive.detekt")
                apply("jisho.primitive.molecule")
            }

            dependencies {
                implementation(libs.findLibrary("accompanist-navigation-animation"))
            }
        }
    }
}