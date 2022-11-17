plugins {
    id("jisho.convention.kmp")
}

android.namespace = "in.surajsau.jisho.model"

kotlin {
    android()
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "model"
        }
    }
}