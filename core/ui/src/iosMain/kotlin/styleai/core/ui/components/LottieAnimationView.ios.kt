package styleai.core.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.UIKitInteropProperties
import androidx.compose.ui.viewinterop.UIKitView

@Composable
actual fun LottieAnimationView(file: String, modifier: Modifier) {

    UIKitView(
        factory = { LottieViewProvider.provideLottieView(file) },
        modifier = modifier,
        properties = UIKitInteropProperties(
            isInteractive = false,
            isNativeAccessibilityEnabled = false
        )
    )

}