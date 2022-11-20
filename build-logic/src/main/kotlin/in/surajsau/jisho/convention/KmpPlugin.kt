package `in`.surajsau.jisho.convention

import org.gradle.api.Plugin
import org.gradle.api.Project

class KmpPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("jisho.primitive.kmp")
                apply("jisho.primitive.kmp.android")
                apply("jisho.primitive.kmp.ios")
                apply("jisho.primitive.koin")
                apply("jisho.primitive.detekt")
            }
        }
    }
}