plugins {
    `kotlin-dsl`
}

group = "in.surajsau.jisho.buildlogic"

repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation(libs.kotlin.gradlePlugin)
    implementation(libs.android.gradlePlugin)
    implementation(libs.play.services.gradlePlugin)
    implementation(libs.moko.resources.gradlePlugin)
    implementation(libs.sqldelight.gradlePlugin)
    implementation(libs.firebase.crahlytics.gradlePlugin)
    implementation(libs.konfig.gradlePlugin)
    implementation(libs.detekt.gradlePlugin)
    implementation(libs.molecule.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "jisho.primitive.android.application"
            implementationClass = "in.surajsau.jisho.primitive.AndroidApplicationPlugin"
        }
        register("androidModule") {
            id = "jisho.primitive.android.module"
            implementationClass = "in.surajsau.jisho.primitive.AndroidModulePlugin"
        }
        register("androidKotlin") {
            id = "jisho.primitive.android.kotlin"
            implementationClass = "in.surajsau.jisho.primitive.AndroidKotlinPlugin"
        }
        register("firebase") {
            id = "jisho.primitive.firebase"
            implementationClass = "in.surajsau.jisho.primitive.AndroidFirebasePlugin"
        }
        register("compose") {
            id = "jisho.primitive.compose"
            implementationClass = "in.surajsau.jisho.primitive.AndroidComposePlugin"
        }
        register("compose.icons") {
            id = "jisho.primitive.compose.icons"
            implementationClass = "in.surajsau.jisho.primitive.AndroidIconsPlugin"
        }
        register("molecule") {
            id = "jisho.primitive.molecule"
            implementationClass = "in.surajsau.jisho.primitive.MoleculePlugin"
        }
        register("kmp") {
            id = "jisho.primitive.kmp"
            implementationClass = "in.surajsau.jisho.primitive.KmpPlugin"
        }
        register("kmpAndroid") {
            id = "jisho.primitive.kmp.android"
            implementationClass = "in.surajsau.jisho.primitive.KmpAndroidPlugin"
        }
        register("kmpIos") {
            id = "jisho.primitive.kmp.ios"
            implementationClass = "in.surajsau.jisho.primitive.KmpIosPlugin"
        }
        register("coroutine") {
            id = "jisho.primitive.coroutine"
            implementationClass = "in.surajsau.jisho.primitive.CoroutinePlugin"
        }
        register("sqldelight") {
            id = "jisho.primitive.sqldelight"
            implementationClass = "in.surajsau.jisho.primitive.SqlDelightPlugin"
        }
        register("koin") {
            id = "jisho.primitive.koin"
            implementationClass = "in.surajsau.jisho.primitive.KmpKoinPlugin"
        }
        register("koin.android") {
            id = "jisho.primitive.koin.android"
            implementationClass = "in.surajsau.jisho.primitive.AndroidKoinPlugin"
        }
        register("detekt") {
            id = "jisho.primitive.detekt"
            implementationClass = "in.surajsau.jisho.primitive.DetektPlugin"
        }
        register("dependencyGraph") {
            id = "jisho.primitive.dependencygraph"
            implementationClass =  "in.surajsau.jisho.primitive.DependencyGraphPlugin"
        }
        register("moko.resources") {
            id = "jisho.primitive.mokoresources"
            implementationClass = "in.surajsau.jisho.primitive.MokoResourcesPlugin"
        }
        register("androidFeature") {
            id = "jisho.convention.android.feature"
            implementationClass = "in.surajsau.jisho.convention.AndroidFeaturePlugin"
        }
        register("convention.kmp") {
            id = "jisho.convention.kmp"
            implementationClass = "in.surajsau.jisho.convention.KmpPlugin"
        }
    }
}