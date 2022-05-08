pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

rootProject.name = "Jisho"
include(":androidApp")
include(":shared")
include(":data")
include(":fake")
