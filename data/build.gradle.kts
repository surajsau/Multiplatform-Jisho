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

        val commonMain by getting {
            dependencies {
                api(libs.koin.core)
                implementation(libs.coroutines.core)
                implementation(libs.sqldelight.runtime)
                implementation(libs.sqldelight.coroutines.extensions)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(libs.coroutines.test)
            }
        }
        val androidMain by getting {
            dependencies {
                api(libs.koin.android)
                implementation(libs.sqldelight.driver.android)
                implementation(libs.coroutines.android)
            }
        }
        val androidTest by getting {
            dependencies {
                implementation("androidx.test.ext:junit-ktx:1.1.3")
                implementation("org.robolectric:robolectric:4.7.3")
                implementation(libs.coroutines.test)
                implementation(libs.sqldelight.driver.common)
            }
        }
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependencies {
                implementation(libs.sqldelight.driver.native)
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
dependencies {
    implementation("androidx.test:core-ktx:1.4.0")
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
