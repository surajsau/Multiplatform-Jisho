package `in`.surajsau.jisho.primitive

import org.gradle.api.Plugin
import org.gradle.api.Project

@Suppress("unused")
class MoleculePlugin: Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("app.cash.molecule")
            }
        }
    }

}