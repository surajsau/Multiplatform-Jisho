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
    ":core:navigation",
    ":core:preference",
    ":core:ui",
    ":core:utils",
    ":data",
    ":fake",
    ":feature:details",
    ":feature:download",
    ":feature:favorites",
    ":feature:reference",
    ":feature:reference:jlpt",
    ":feature:reference:kanji",
    ":feature:search",
    ":feature:sentence:details",
    ":feature:sentence:list",
    ":feature:settings"
)
include(":core:model")
include(":core:model")
include(":core:app")
include(":feature:acknowledgement")
