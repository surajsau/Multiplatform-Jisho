package `in`.surajsau.jisho.primitive

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.nativeplatform.platform.internal.DefaultNativePlatform
import java.io.File

@Suppress("unused")
class MokoResourcesPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("dev.icerock.mobile.multiplatform-resources")

            android {
                sourceSets.getByName("main").apply {
                    val os = DefaultNativePlatform.getCurrentOperatingSystem()
                    if (os.isMacOsX) {
                        assets.srcDir(File(buildDir, "generated/moko/androidMain/assets"))
                        res.srcDir(File(buildDir, "generated/moko/androidMain/res"))
                    }
                }
            }
        }
    }
}