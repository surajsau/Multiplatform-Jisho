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
    ":core:neumorphic",
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
