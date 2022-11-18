pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

rootProject.name = "Jisho"

enableFeaturePreview("VERSION_CATALOGS")
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

include(
    ":androidApp",
    ":shared",
    ":core:app",
    ":core:model",
    ":core:navigation",
    ":core:preference",
    ":core:ui",
    ":core:utils",
    ":data",
    ":fake",
    ":feature:details",
    ":feature:download",
    ":feature:favorites",
    ":feature:acknowledgement",
    ":feature:reference",
    ":feature:jlpt",
    ":feature:kanji",
    ":feature:search",
    ":feature:sentence:details",
    ":feature:sentence:list",
    ":feature:settings"
)
