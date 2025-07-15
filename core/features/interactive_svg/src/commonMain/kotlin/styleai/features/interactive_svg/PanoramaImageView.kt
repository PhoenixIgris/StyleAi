package styleai.features.interactive_svg

import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import styleai.core.resources.Res
import styleai.core.resources.panorama

@Composable
fun PanoramaImageView(
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()

    Row(
        modifier = Modifier.fillMaxSize()
            .horizontalScroll(scrollState)
            .height(200.dp)
    ) {
        Image(
            painter = painterResource( Res.drawable.panorama),
            contentDescription = "Panorama",
            contentScale = ContentScale.FillHeight, // preserve height, scroll width
            modifier = Modifier
                .fillMaxSize()
        )
    }
}
