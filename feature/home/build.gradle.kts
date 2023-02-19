plugins {
    id("jisho.convention.android.feature")
}

android.namespace = "in.surajsau.jisho.home"

dependencies {
    implementation(projects.shared)
    implementation(projects.core.ui)
    implementation(projects.core.utils)
    implementation(projects.core.model)

    implementation(projects.feature.home.search)
    implementation(projects.feature.home.tagged)
    implementation(projects.feature.home.reference)
    implementation(projects.feature.home.settings)
}