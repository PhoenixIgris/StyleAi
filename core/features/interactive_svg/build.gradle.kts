plugins {
    alias(libs.plugins.styleai.feature.convention.plugin)
}


kotlin {
    sourceSets.androidMain.dependencies {
        implementation(libs.androidx.appcompat)
        implementation(libs.androidx.activity)
        implementation("androidx.webkit:webkit:1.14.0")
    }
}



