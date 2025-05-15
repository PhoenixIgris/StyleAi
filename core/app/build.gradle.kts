import styleai.libs

plugins {
    alias(libs.plugins.styleai.multiplatform.compose.library.convention.plugin)
}

kotlin {

    sourceSets.androidMain.dependencies {
        implementation(libs.androidx.core.ktx)
        implementation(libs.androidx.activity.compose)
    }

}
