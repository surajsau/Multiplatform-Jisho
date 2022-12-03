plugins {
    id("jisho.convention.android.feature")
}

android.namespace = "in.surajsau.jisho.ui"

dependencies {
    implementation(projects.core.model)
}