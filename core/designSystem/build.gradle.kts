import styleai.namespace


plugins {
    alias(libs.plugins.styleai.multiplatform.compose.library.convention.plugin)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.common)
            implementation(projects.core.resources)
        }
    }
}

compose.resources {
    packageOfResClass = namespace
}

android {
    androidResources {
        this.noCompress.addAll(listOf())
    }
}
