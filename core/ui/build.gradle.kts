plugins {
    id("jisho.convention.android.feature")
}

android.namespace = "in.surajsau.jisho.neumorphic"

dependencies {
    implementation(projects.core.model)
}