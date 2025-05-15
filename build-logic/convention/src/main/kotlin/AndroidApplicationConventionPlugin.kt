import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import styleai.apply
import styleai.configureAndroidCompose
import styleai.configureKotlinAndroid
import styleai.configureKotlinCompilation
import styleai.configureTestLogging
import styleai.libs

class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project): Unit = with(target) {
        with(pluginManager) {
            apply(libs.plugins.androidApplication)
            apply(libs.plugins.kotlinAndroid)
            apply(libs.plugins.styleai.module.convention.plugin)
        }

        configure<ApplicationExtension> {
            configureKotlinAndroid(this)
            configureAndroidCompose(this)
            configureKotlinCompilation()
        }

        dependencies {
            add("testImplementation", libs.junit.get())
        }

        configureTestLogging()
    }
}
