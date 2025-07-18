package styleai.features.interactive_svg

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.UIKitViewController
import platform.UIKit.UIViewController

@Composable
actual fun PanoramaImageView(modifier: Modifier) {
    UIKitViewController(
        modifier = modifier,
        factory = {
            PanoramaImageProvider.providePanoramaImage()
        }
    )
}

object PanoramaImageProvider {
    var providePanoramaImage: () -> UIViewController  = { UIViewController() }
}

