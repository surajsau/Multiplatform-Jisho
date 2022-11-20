plugins {
    id("jisho.convention.android.feature")
}

android.namespace = "in.surajsau.jisho.sentence"

dependencies {
    implementation(project(":shared"))

    implementation(projects.core.model)
    implementation(projects.core.utils)
    implementation(projects.core.ui)
}