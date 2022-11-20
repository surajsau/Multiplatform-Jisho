plugins {
    id("jisho.convention.android.feature")
}

android.namespace = "in.surajsau.jisho.search"

dependencies {
    implementation(projects.core.model)
    implementation(project(":core:utils"))
    implementation(project(":core:ui"))
    implementation(project(":shared"))
}