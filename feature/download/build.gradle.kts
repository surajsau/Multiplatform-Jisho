plugins {
    id("jisho.convention.android.feature")
}

android.namespace = "in.surajsau.jisho.download"

dependencies {
    val firebase = "29.3.1"

    implementation(project(":core:utils"))
    implementation(project(":core:navigation"))
    implementation(project(":shared"))

    implementation(platform("com.google.firebase:firebase-bom:$firebase"))
    implementation("com.google.firebase:firebase-storage-ktx")
}