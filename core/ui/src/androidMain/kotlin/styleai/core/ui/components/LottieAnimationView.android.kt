package styleai.core.ui.components


import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
actual fun LottieAnimationView(file: String, modifier: Modifier) {
    val composition by rememberLottieComposition(LottieCompositionSpec.Asset(file))
    val progress by animateLottieCompositionAsState(
        composition, iterations = LottieConstants.IterateForever, isPlaying = true
    )
    LottieAnimation(
        composition, { progress },
        modifier = modifier
    )
}