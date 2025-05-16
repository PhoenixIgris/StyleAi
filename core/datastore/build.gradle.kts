plugins {
    alias(libs.plugins.styleai.multiplatform.compose.library.convention.plugin)
}

kotlin {
    sourceSets.commonMain.dependencies {
        api(libs.multiplatformSettings)
    }

    sourceSets.androidMain.dependencies {
        implementation(libs.contextProvider)
    }
}
