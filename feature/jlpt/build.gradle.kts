plugins {
    id("jisho.convention.android.feature")
}

android.namespace = "in.surajsau.jisho.reference.jlpt"

dependencies {
    implementation(projects.shared)
    implementation(projects.core.ui)
    implementation(projects.core.utils)
    implementation(projects.core.model)
}