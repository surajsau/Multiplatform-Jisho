plugins {
    id("jisho.convention.android.feature")
}

android.namespace = "in.surajsau.jisho.feature.download"

dependencies {
    implementation(projects.core.ui)
    implementation(projects.core.utils)
    implementation(projects.shared)

    implementation(projects.core.download)
}