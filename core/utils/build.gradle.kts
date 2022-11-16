plugins {
    id("jisho.convention.android.feature")
}

android.namespace = "in.surajsau.jisho.utils"

dependencies {
    implementation(project(":shared"))
}