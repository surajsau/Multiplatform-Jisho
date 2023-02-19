package `in`.surajsau.jisho.primitive

import app.cash.sqldelight.gradle.SqlDelightExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

@Suppress("Unused")
class SqlDelightPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("app.cash.sqldelight")
            }

            kotlin {
                commonMain {
                    dependencies {
                        implementation(libs.findLibrary("sqldelight-coroutines-extensions").get())
                    }
                }
                androidMain {
                    dependencies {
                        implementation(libs.findLibrary("sqldelight-driver-android").get())
                    }
                }
                iosMain {
                    dependencies {
                        implementation(libs.findLibrary("sqldelight-driver-native").get())
                    }
                }
            }

            setup()
        }
    }
}

private fun Project.setup() {
    extensions.configure<SqlDelightExtension>("sqldelight") {
        databases.create("Jisho") {
            packageName.set("in.surajsau.jisho.data.db")
            sourceFolders.set(listOf("sqldelight"))
        }
    }
}