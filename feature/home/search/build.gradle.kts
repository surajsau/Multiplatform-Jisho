plugins {
    id("jisho.convention.android.feature")
}

android.namespace = "in.surajsau.jisho.search"

dependencies {
    implementation(projects.core.model)
    implementation(projects.core.utils)
    implementation(projects.core.ui)
    implementation(projects.shared)
}