package `in`.surajsau.jisho.convention

import org.gradle.api.Plugin
import org.gradle.api.Project

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
        }
    }
}