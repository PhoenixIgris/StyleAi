import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget
import styleai.namespace


plugins {
    alias(libs.plugins.styleai.multiplatform.compose.library.convention.plugin)
}

val binaryName = "StyleAi"

fun KotlinNativeTarget.configureBinary() {
    binaries.framework {
        baseName = binaryName
        isStatic = true

        binaryOption("bundleId", namespace)

        export(projects.core.app)
        export(projects.core.ui)
    }
}

kotlin {

    iosArm64().configureBinary()
    iosSimulatorArm64().configureBinary()

    sourceSets {
        commonMain.dependencies {
            api(projects.core.app)
        }
    }
}
