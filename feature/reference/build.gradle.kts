plugins {
    id("jisho.convention.android.feature")
}

android.namespace = "in.surajsau.jisho.reference"

dependencies {
    implementation(project(":shared"))

    implementation(projects.core.model)
    implementation(project(":core:utils"))
    implementation(project(":core:ui"))
    implementation(project(":core:navigation"))
}