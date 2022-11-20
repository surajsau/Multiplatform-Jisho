plugins {
    id("jisho.convention.kmp")
}

android.namespace = "in.surajsau.jisho.fake"

kotlin {
    android()
    ios()
    iosSimulatorArm64()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":data"))
            }
        }
    }
}