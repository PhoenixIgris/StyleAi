package styleai.features.interactive_svg

import SVGData
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
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
import androidx.compose.ui.unit.dp
import panda.core.components.coil.LazyImage

@Composable
fun SvgInteractionView(image: String?) {
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


            LazyImage(image, modifier = Modifier.wrapContentSize())

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