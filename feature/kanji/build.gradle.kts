plugins {
    id("jisho.convention.android.feature")
}

android.namespace = "in.surajsau.jisho.reference.kanji"

dependencies {
    implementation(projects.core.ui)
    implementation(projects.shared)
}