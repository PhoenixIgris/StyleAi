package styleai.features.interactive_svg

import SVGData
import androidx.compose.runtime.Composable

@Composable
expect fun parsePathsFromVectorDrawable(): List<SVGData>