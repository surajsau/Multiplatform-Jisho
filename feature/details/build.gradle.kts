plugins {
    id("jisho.convention.android.feature")
}

android.namespace = "in.surajsau.jisho.details"

dependencies {
    implementation(projects.core.model)
    implementation(projects.core.utils)
    implementation(projects.core.ui)

    implementation(projects.shared)
}