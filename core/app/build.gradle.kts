plugins {
    alias(libs.plugins.styleai.multiplatform.compose.library.convention.plugin)
    alias(libs.plugins.kotlinx.serilization)
}

kotlin {
    sourceSets.commonMain.dependencies {
        implementation(projects.core.common)
        implementation(projects.core.analytics)
        implementation(projects.core.designSystem)
        implementation(projects.core.ui)
        implementation(projects.core.data)
        implementation(projects.core.features.main)
        implementation(projects.core.features.interactiveSvg)
        api(projects.core.features.camera)
        implementation(libs.jetbrain.navigation.compose)
    }
}
