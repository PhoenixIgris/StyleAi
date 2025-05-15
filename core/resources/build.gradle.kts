import styleai.namespace


plugins {
    alias(libs.plugins.styleai.multiplatform.compose.library.convention.plugin)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.common)
        }
    }
}

compose.resources {
    publicResClass = true
    packageOfResClass = namespace
    generateResClass = always
}
