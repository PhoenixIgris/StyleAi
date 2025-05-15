plugins {
    alias(libs.plugins.styleai.multiplatform.library.convention.plugin)
}

kotlin {

    sourceSets.commonMain.dependencies {
        api(libs.napier)
    }
}
