plugins {
    alias(libs.plugins.styleai.multiplatform.compose.library.convention.plugin)

}


kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.datastore)
            implementation(projects.core.database)
            implementation(projects.core.common)
            implementation(projects.core.network)
            api(projects.core.models)
            implementation(projects.core.resources)
        }
    }
}