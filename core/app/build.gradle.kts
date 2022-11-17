plugins {
    id("jisho.convention.android.feature")
}

android.namespace = "in.surajsau.jisho.app"

dependencies {
    implementation(projects.core.model)
    implementation(projects.core.navigation)
    implementation(projects.core.preference)

    implementation(projects.feature.search)
}