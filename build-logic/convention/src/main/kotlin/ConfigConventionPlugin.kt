import com.codingfeline.buildkonfig.compiler.FieldSpec
import com.codingfeline.buildkonfig.gradle.BuildKonfigExtension
import com.codingfeline.buildkonfig.gradle.TargetConfigDsl
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.extra
import org.gradle.kotlin.dsl.support.uppercaseFirstChar
import styleai.VariantDimension.dev
import styleai.VariantDimension.prod
import styleai.apply
import styleai.getAndroidBuildVariantOrNull
import styleai.libs
import styleai.namespace
import java.util.Properties

class ConfigConventionPlugin : Plugin<Project> {
    override fun apply(target: Project): Unit = with(target) {
        with(pluginManager) {
            apply(libs.plugins.styleai.multiplatform.library.convention.plugin)
            apply(libs.plugins.buildkonfig)
        }

        project.extra.set("buildkonfig.flavor", currentBuildVariant())

        extensions.configure<BuildKonfigExtension> {
            this.exposeObjectWithName
            objectName = "StyleAiConfig"
            exposeObjectWithName = "StyleAiConfig"
            packageName = target.namespace

            defaultConfigs {
                field("variant", dev)
                styleaiProperties("development.styleai.config")
            }

            defaultConfigs(dev) {
                field("variant", dev)
                styleaiProperties("development.styleai.config")
            }

            defaultConfigs(prod) {
                field("variant", prod)
                styleaiProperties("production.styleai.config")
            }
        }
    }
}

context(Project)
private fun TargetConfigDsl.styleaiProperties(file: String) {
    val properties = Properties().apply {
        val propertiesFile =
            rootProject.layout.projectDirectory.file("config/styleai/$file").asFile
        load(propertiesFile.inputStream())
    }

    properties.stringPropertyNames()
        .forEach { key ->
            field(key.asConfigKey(), properties.getProperty(key))
        }
}

private fun String.asConfigKey() = this.split(".", "-")
    .mapIndexed { index: Int, s: String -> if (index == 0) s else s.uppercaseFirstChar() }
    .joinToString("")

private fun <T> TargetConfigDsl.field(key: String, value: T) {
    val spec = when (value) {
        is String -> FieldSpec.Type.STRING
        is Int -> FieldSpec.Type.INT
        is Float -> FieldSpec.Type.FLOAT
        is Long -> FieldSpec.Type.LONG
        is Boolean -> FieldSpec.Type.BOOLEAN
        else -> error("Unsupported build config value '$value' for '$key'")
    }

    buildConfigField(spec, key, value.toString().trim().removeSurrounding("\""))
}

private fun Project.currentBuildVariant(): String {
    val variants = setOf(dev, prod)

    return getAndroidBuildVariantOrNull()
        ?: System.getenv()["VARIANT"]
            .toString()
            .takeIf { it in variants } ?: dev
}
