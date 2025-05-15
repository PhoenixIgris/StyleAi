plugins {
    alias(libs.plugins.styleai.multiplatform.compose.library.convention.plugin)
}

kotlin {
    sourceSets.commonMain.dependencies {
        implementation(projects.core.common)
        implementation(projects.core.analytics)
    }
}
