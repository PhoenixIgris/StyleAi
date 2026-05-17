plugins {
    alias(libs.plugins.styleai.feature.convention.plugin)
}

kotlin {
    sourceSets.commonMain.dependencies {
        implementation(projects.core.data)
    }
}
