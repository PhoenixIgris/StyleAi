import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import styleai.apply
import styleai.libs

class FeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply(libs.plugins.styleai.multiplatform.compose.library.convention.plugin)
            }


            configure<KotlinMultiplatformExtension> {
                sourceSets.commonMain.dependencies {
                    implementation(project(":core:ui"))
                    implementation(project(":core:designSystem"))
                    implementation(project(":core:common"))
                    implementation(project(":core:resources"))
                    implementation(libs.kotlinx.serialization.json)
                    implementation(libs.result.get())
                }

            }

        }
    }
}
