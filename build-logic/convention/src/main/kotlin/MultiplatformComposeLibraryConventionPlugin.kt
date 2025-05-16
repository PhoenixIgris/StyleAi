import com.android.build.api.dsl.ApplicationExtension
import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.compose.ComposeExtension
import org.jetbrains.compose.ComposePlugin
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import styleai.apply
import styleai.configureAndroidCompose
import styleai.configureKotlinAndroid
import styleai.libs

class MultiplatformComposeLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project): Unit = with(target) {
        with(pluginManager) {
            apply(libs.plugins.styleai.multiplatform.library.convention.plugin)
            apply(libs.plugins.jetbrainsCompose)
            apply(libs.plugins.compose.compiler)
        }

        configure<LibraryExtension> {
            configureKotlinAndroid(this)
            configureAndroidCompose(this)
        }

        configure<KotlinMultiplatformExtension> {
            val extension = (extensions.getByName("compose") as ComposePlugin.Dependencies)

            sourceSets.commonMain.dependencies {
                implementation(extension.material3)
                implementation(extension.ui)
                implementation(extension.foundation)
                implementation(extension.runtime)
                implementation(extension.materialIconsExtended)
                implementation(extension.components.resources)
                implementation(extension.components.uiToolingPreview)
            }

        }

    }
}
