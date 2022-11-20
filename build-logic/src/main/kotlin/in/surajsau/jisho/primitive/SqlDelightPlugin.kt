package `in`.surajsau.jisho.primitive

import com.squareup.sqldelight.gradle.SqlDelightExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

@Suppress("Unused")
class SqlDelightPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.squareup.sqldelight")
            }

            kotlin {
                commonMain {
                    dependencies {
                        implementation(libs.findLibrary("sqldelight-runtime").get())
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
        database("Jisho") {
            packageName = "in.surajsau.jisho.data.db"
            sourceFolders = listOf("sqldelight")
        }
    }
}