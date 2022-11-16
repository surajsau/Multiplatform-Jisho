package `in`.surajsau.jisho.primitive

import com.android.build.gradle.TestedExtension
import org.gradle.api.plugins.ExtensionAware
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet

fun TestedExtension.kotlinOptions(block: KotlinJvmOptions.() -> Unit) {
    (this as ExtensionAware).extensions.configure("kotlinOptions", block)
}

fun KotlinMultiplatformExtension.commonMain(block: KotlinSourceSet.() -> Unit) {
    block(sourceSets.getByName("commonMain"))
}

fun KotlinMultiplatformExtension.androidMain(block: KotlinSourceSet.() -> Unit) {
    block(sourceSets.getByName("androidMain"))
}

fun KotlinMultiplatformExtension.iosMain(block: KotlinSourceSet.() -> Unit) {
    block(sourceSets.getByName("iosMain"))
}