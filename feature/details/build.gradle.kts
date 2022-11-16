plugins {
    id("jisho.convention.android.feature")
}

android.namespace = "in.surajsau.jisho.details"

dependencies {
    implementation(project(":core:utils"))
    implementation(project(":core:navigation"))
    implementation(project(":core:ui"))

    implementation(project(":shared"))
}