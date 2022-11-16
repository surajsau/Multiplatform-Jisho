plugins {
    id("jisho.convention.android.feature")
}

android.namespace = "in.surajsau.jisho.reference.jlpt"

dependencies {
    implementation(project(":shared"))
    implementation(project(":core:ui"))
    implementation(project(":core:navigation"))
    implementation(project(":core:neumorphic"))
    implementation(project(":shared"))
}