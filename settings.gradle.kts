enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}

rootProject.name = "StyleAi"
include(":android")
include(":core")
include(":core:app")
include(":core:analytics")
include(":core:common")
include(":core:resources")
include(":core:designSystem")
include(":core:ui")
include(":core:datastore")
include(":core:models")