plugins {
    id("jisho.convention.android.feature")
}

android.namespace = "in.surajsau.jisho.app"

dependencies {
    implementation(projects.core.model)
    implementation(projects.core.preference)
    implementation(projects.download)

    implementation(projects.feature.download)
    implementation(projects.feature.home)

    implementation(projects.feature.home.search)
}