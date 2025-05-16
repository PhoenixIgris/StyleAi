package styleai.core.ui

import org.jetbrains.compose.resources.ExperimentalResourceApi
import styleai.core.common.Platform
import styleai.core.common.platform
import styleai.core.ui.generated.resources.Res


object LottieAnimations {

}

@OptIn(ExperimentalResourceApi::class)
private fun getAssetFilePath(name: String): String {
    return when (platform()) {
        Platform.Android -> Res.getUri("files/$name").removePrefix("file:///android_asset/")
        Platform.IOS -> Res.getUri("files/$name")
    }
}