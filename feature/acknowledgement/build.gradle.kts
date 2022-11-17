plugins {
    id("jisho.convention.android.feature")
}

android.namespace = "in.surajsau.jisho.acknowledgement"

dependencies {
    implementation(projects.core.model)
    implementation(project(":core:utils"))
    implementation(project(":core:navigation"))
    implementation(project(":core:ui"))

    implementation(project(":shared"))
}