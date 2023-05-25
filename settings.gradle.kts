pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

rootProject.name = "Jisho"

include(
    ":androidApp",
    ":shared",
    ":core:app",
    ":core:data",
    ":core:model",
    ":core:preference",
    ":core:ui",
    ":core:utils",
    ":core:viewmodel",
    ":core:download",
    ":fake",
    ":feature:acknowledgement",
    ":feature:details",
    ":feature:download",
    ":feature:home",
    ":feature:home:tagged",
    ":feature:home:reference",
    ":feature:home:search",
    ":feature:home:settings",
    ":feature:jlpt",
    ":feature:kanji",
    ":feature:sentence"
)
