plugins {
    id("jisho.convention.android.feature")
}

android.namespace = "in.surajsau.jisho.favorites"

dependencies {
    implementation(project(":core:utils"))
    implementation(project(":core:ui"))
    implementation(project(":shared"))

    implementation(libs.compose.icons.extended)
}