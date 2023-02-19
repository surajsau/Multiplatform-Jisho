plugins {
    id("jisho.convention.android.feature")
}

android.namespace = "in.surajsau.jisho.settings"

dependencies {
    implementation(projects.core.utils)
    implementation(projects.core.ui)
    implementation(projects.shared)
}