package styleai.features.interactive_svg


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

private const val TAG = "InteractiveScreen"

@Composable
fun InteractiveScreen(
) {
    var isPanorama by remember { mutableStateOf(false) }
    Column {
        Button(
            onClick = {
                isPanorama = false
            },
            modifier = Modifier.fillMaxWidth().wrapContentHeight(),
        ) {
            Text(text = "Svg Interaction")
        }

        Button(
            onClick = {
                isPanorama = true
            },
            modifier = Modifier.fillMaxWidth().wrapContentHeight(),
        ) {
            Text(text = "Panorama Interaction")
        }
        if (isPanorama) {
            PanoramaImageView(modifier = Modifier.fillMaxSize())
        } else {
            SvgInteractionView()
        }
    }
}
