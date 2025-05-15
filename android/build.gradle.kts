import styleai.VariantDimension
import styleai.VariantDimension.dev
import styleai.VariantDimension.prod

plugins {
    alias(libs.plugins.styleai.android.application.convention.plugin)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "styleai"

    defaultConfig {
        applicationId = "com.igris.styleai"
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        debug {
            isMinifyEnabled = false
            isDebuggable = true
        }
    }

    flavorDimensions.add(VariantDimension.name)

    productFlavors {
        create(dev) {
            dimension = VariantDimension.name
            isDefault = true
            applicationIdSuffix = ".dev"
            resValue("string", "app_name", "StyleAi")
        }

        create(prod) {
            dimension = VariantDimension.name
        }
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.activity.compose)
    implementation(projects.core.app)
    implementation(libs.koin.core)
    implementation(libs.koin.android)
}
