plugins {
    id("jisho.convention.android.feature")
}

android.namespace = "in.surajsau.jisho.settings"

dependencies {
    implementation(project(":core:utils"))
    implementation(project(":core:ui"))
    implementation(project(":core:navigation"))
    implementation(project(":shared"))
}