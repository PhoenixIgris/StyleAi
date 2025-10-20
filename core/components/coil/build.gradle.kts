plugins {
    alias(libs.plugins.styleai.multiplatform.compose.library.convention.plugin)
}

kotlin {
    sourceSets.commonMain.dependencies {
        implementation(projects.core.resources)
        implementation(projects.core.designSystem)
        api(libs.coil.compose.core)
        api(libs.coil.compose)
        api(libs.coil.mp)
        api(libs.coil.network.ktor)

    }

}
