plugins {
    id("jisho.convention.android.feature")
}

android.namespace = "in.surajsau.jisho.reference.kanji"

dependencies {
    implementation(projects.core.model)

    implementation(projects.shared)
    implementation(projects.core.ui)
    implementation(projects.core.utils)

    implementation(projects.shared)
}