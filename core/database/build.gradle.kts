plugins {
    alias(libs.plugins.styleai.multiplatform.library.convention.plugin)
    alias(libs.plugins.kotlinx.serilization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.styleai.android.room.convention.plugin)

}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.common)
            implementation(projects.core.models)
        }
        androidMain.dependencies {
            implementation(libs.koin.android)
        }

    }
}