plugins {
    alias(libs.plugins.styleai.feature.convention.plugin)
}


kotlin {
    sourceSets.androidMain.dependencies {
        implementation(libs.androidx.activity.compose)
        implementation(libs.accompanist.permissions)
    }
}