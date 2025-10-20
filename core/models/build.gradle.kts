import styleai.libs

plugins {
    alias(libs.plugins.styleai.multiplatform.library.convention.plugin)
    alias(libs.plugins.kotlinx.serilization)
}
kotlin{
    sourceSets.commonMain.dependencies {
        api(libs.room.runtime.get())
    }
}