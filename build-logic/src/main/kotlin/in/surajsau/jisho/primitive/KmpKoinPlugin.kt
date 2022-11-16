package `in`.surajsau.jisho.primitive

import org.gradle.api.Plugin
import org.gradle.api.Project

@Suppress("Unused")
class KmpKoinPlugin: Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            kotlin {
                commonMain {
                    dependencies {
                        implementation(libs.findLibrary("koin-core").get())
                    }
                }

                androidMain {
                    dependencies {
                        implementation(libs.findLibrary("koin-android").get())
                    }
                }
            }
        }
    }
}