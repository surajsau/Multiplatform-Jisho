plugins {
    id("jisho.convention.android.feature")
}

android.namespace = "in.surajsau.jisho.tagged"

dependencies {
    implementation(projects.core.utils)
    implementation(projects.core.ui)
    implementation(projects.shared)

    implementation(libs.compose.icons.extended)
}