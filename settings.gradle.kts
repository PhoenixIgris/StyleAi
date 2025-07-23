enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
        maven { url = uri("https://developer.huawei.com/repo/") }
        maven("https://maven.pkg.jetbrains.space/kotlin/p/wasm/experimental")
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
        maven { url = uri("https://developer.huawei.com/repo/") }

        maven("https://maven.pkg.jetbrains.space/kotlin/p/wasm/experimental")
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
include(":core:data")
include(":core:network")
include(":core:database")
include(":core:features:main")
include(":core:features:camera")
include(":core:features:interactive_svg")