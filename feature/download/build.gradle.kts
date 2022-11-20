plugins {
    id("jisho.convention.android.feature")
}

android.namespace = "in.surajsau.jisho.feature.download"

dependencies {

    implementation(projects.core.utils)
    implementation(project(":shared"))

    implementation(projects.core.download)
}