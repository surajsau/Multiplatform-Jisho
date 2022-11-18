plugins {
    id("jisho.convention.android.feature")
}

android.namespace = "in.surajsau.jisho.reference.kanji"

dependencies {
    implementation(projects.core.model)

    implementation(project(":shared"))
    implementation(project(":core:ui"))
    implementation(project(":core:utils"))
    implementation(project(":core:navigation"))

    implementation(project(":shared"))
}