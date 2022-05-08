plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("com.squareup.sqldelight")
    id("com.rickclephas.kmp.nativecoroutines") version "0.11.4-new-mm"
    id("org.jlleitschuh.gradle.ktlint") version "10.2.1"
}

kotlin {
    android()

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "data"
        }
    }

    sourceSets {
        val sqldelight = "1.5.3"
        val koin = "3.1.6"
        val coroutine = "1.6.0"

        val commonMain by getting {
            dependencies {
                api("io.insert-koin:koin-core:$koin")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutine")
                implementation("com.squareup.sqldelight:runtime:$sqldelight")
                implementation("com.squareup.sqldelight:coroutines-extensions:$sqldelight")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependencies {
                api("io.insert-koin:koin-android:$koin")
                implementation("com.squareup.sqldelight:android-driver:$sqldelight")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutine")
            }
        }
        val androidTest by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependencies {
                implementation("com.squareup.sqldelight:native-driver:$sqldelight")
            }
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

sqldelight {
    database("Jisho") {
        packageName = "in.surajsau.jisho.data.db"
        sourceFolders = listOf("sqldelight")
    }
}