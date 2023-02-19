plugins {
    id("jisho.convention.android.feature")
}

android.namespace = "in.surajsau.jisho.app"

dependencies {
    implementation(projects.core.model)
    implementation(projects.core.preference)
    implementation(projects.download)
    implementation(projects.core.viewmodel)

    implementation(projects.feature.download)
    implementation(projects.feature.home)

    implementation(projects.feature.home.search)

    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.common)
    implementation(libs.firebase.storage)
}