plugins {
    id("jisho.convention.android.feature")
}

android.namespace = "in.surajsau.jisho.neumorphic"

dependencies {
    implementation(project(":core:neumorphic"))
}