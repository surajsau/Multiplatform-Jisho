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
    ":core:download",
    ":core:model",
    ":core:preference",
    ":core:ui",
    ":core:utils",
    ":data",
    ":fake",
    ":feature:acknowledgement",
    ":feature:details",
    ":feature:download",
    ":feature:home",
    ":feature:home:favorites",
    ":feature:home:reference",
    ":feature:home:search",
    ":feature:home:settings",
    ":feature:jlpt",
    ":feature:kanji",
    ":feature:sentence"
)
include()
include()
