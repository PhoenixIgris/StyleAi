package styleai.features.interactive_svg


import SVGData
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Matrix
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.vector.PathParser
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import styleai.core.features.interactive_svg.generated.resources.seat
import styleai.core.resources.Res
import styleai.core.resources.panorama

private const val TAG = "InteractiveScreen"

@Composable
fun InteractiveScreen(
) {
    val selectablePaths = parsePathsFromVectorDrawable()
    var selectedSeatName by remember { mutableStateOf("") }
    var selectedSeat by remember { mutableStateOf<List<SVGData>>(emptyList()) }
    LaunchedEffect(selectedSeatName) {
        selectedSeat = selectablePaths.filter { it.name == selectedSeatName }
    }
    ZoomableView {
        Box(
            Modifier
                .fillMaxSize()
        ) {

            Image(
                painter = painterResource(styleai.core.features.interactive_svg.generated.resources.Res.drawable.seat),
                contentDescription = "",
                modifier = Modifier.wrapContentSize()
            )



            Canvas(
                modifier = Modifier
                    .size(500.dp)
                    .pointerInput(Unit) {
                        detectTapGestures { offset ->
                            val selectedSeats = mutableListOf<SVGData>()

                            val scaleX = size.width / 500f
                            val scaleY = size.height / 500f
                            val scale = minOf(scaleX, scaleY)

                            val matrix = Matrix()
                            matrix.reset()
                            matrix.scale(scale, scale)

                            selectablePaths.forEach {
                                val originalPath =
                                    PathParser().parsePathString(it.pathData)
                                        .toPath()
                                val scaledPath = Path().apply {
                                    addPath(originalPath)
                                    transform(matrix)
                                }
                                val bounds = scaledPath.getBounds()
                                if (bounds.contains(offset)) {
                                    selectedSeatName = it.name
                                    return@forEach
                                }
                            }

                            selectedSeat = selectedSeats

                        }
                    }
            ) {
                val scaleX = size.width / 500f
                val scaleY = size.height / 500f
                val scale = minOf(scaleX, scaleY)

                val matrix = Matrix()
                matrix.reset()
                matrix.scale(scale, scale)
                selectedSeat.forEach {
                    val originalPath =
                        PathParser().parsePathString(it.pathData).toPath()
                    val scaledPath = Path().apply {
                        addPath(originalPath)
                        transform(matrix)
                    }
                    drawPath(path = scaledPath, color = Color.Gray)
                }
            }

        }
    }

}
