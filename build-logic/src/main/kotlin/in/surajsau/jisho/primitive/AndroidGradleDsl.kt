package `in`.surajsau.jisho.primitive

import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.TestedExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

fun Project.application(action: BaseAppModuleExtension.() -> Unit) {
    extensions.configure(action)
}

fun Project.androidModule(action: LibraryExtension.() -> Unit) {
    extensions.configure(action)
}

fun Project.android(action: TestedExtension.() -> Unit) {
    extensions.configure(action)
}

fun Project.setupAndroid() {
    android {
        namespace?.let { this.namespace = it }
        compileSdkVersion(33)

        defaultConfig {
            minSdk = 26
            targetSdk = 33

            versionCode = 1
            versionName = "1.0.0"
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
            isCoreLibraryDesugaringEnabled = true
        }

        dependencies {
            add("coreLibraryDesugaring", libs.findLibrary("android-desugar").get())
        }

        testOptions {
            unitTests {
                isIncludeAndroidResources = true
            }
        }
    }
}