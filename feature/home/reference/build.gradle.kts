plugins {
    id("jisho.convention.android.feature")
}

android.namespace = "in.surajsau.jisho.reference"

dependencies {
    implementation(projects.shared)

    implementation(projects.core.model)
    implementation(projects.core.utils)
    implementation(projects.core.ui)
}