plugins {
    kotlin("multiplatform")
    kotlin("kapt")
    id("com.android.library")
    id("com.rickclephas.kmp.nativecoroutines") version "0.11.4-new-mm"
    id("org.jlleitschuh.gradle.ktlint") version "10.2.1"
    id("dev.icerock.mobile.multiplatform-resources")
}

kotlin {
    android()

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            linkerOpts.add("-lsqlite3")
        }
    }

    sourceSets {
        val koin = "3.1.6"
        val coroutine = "1.6.0"
        val klogger = "2.2.0"
        val resources = "0.19.1"

        val commonMain by getting {
            dependencies {
                api("io.insert-koin:koin-core:$koin")
                api("dev.icerock.moko:resources:$resources")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutine")
                implementation(project(":data"))
                implementation(project(":fake"))
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutine")
            }
        }
        val androidMain by getting {
            dependencies {
                api("dev.icerock.moko:resources-compose:$resources")
                api("androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.1")
                api("io.insert-koin:koin-android:$koin")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutine")
            }
        }
        val androidTest by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
    }
}

android {
    compileSdk = 32
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 23
        targetSdk = 32
    }
}

kotlin.targets.withType(org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget::class.java) {
    binaries.all {
        binaryOptions["memoryModel"] = "experimental"
    }
}

ktlint {
    verbose.set(true)
    outputToConsole.set(true)
    enableExperimentalRules.set(true)
    disabledRules.set(setOf("final-newline", "filename"))
    filter {
        exclude("**/generated/**")
        include("**/kotlin/**")
    }
}

multiplatformResources {
    multiplatformResourcesPackage = "in.surajsau.jisho"
    multiplatformResourcesClassName = "Res"
}