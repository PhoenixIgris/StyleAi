package styleai.features.interactive_svg

import SVGData
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
actual fun parsePathsFromVectorDrawable(
): List<SVGData> {
    return SvgDataListProvider.provideSvgData.invoke("seatsvg")
        .map { SVGData(name = it.name, it.pathData, Color.Gray) }
}


object SvgDataListProvider {
    var provideSvgData: (String) -> List<SVGDataIOS> = { emptyList<SVGDataIOS>() }
}

data class SVGDataIOS(
    val name: String, val pathData: String
)