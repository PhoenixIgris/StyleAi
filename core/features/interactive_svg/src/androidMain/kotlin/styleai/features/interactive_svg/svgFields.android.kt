package styleai.features.interactive_svg

import SVGData
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.core.graphics.toColorInt
import org.w3c.dom.Element
import org.w3c.dom.Node
import styleai.core.features.interactive_svg.R
import javax.xml.parsers.DocumentBuilderFactory

@Composable
actual fun parsePathsFromVectorDrawable(
): List<SVGData> {

    val result = mutableListOf<SVGData>()
    val context = LocalContext.current
    val inputStream = context.resources.openRawResource(R.raw.seatsvg)
    val builder = DocumentBuilderFactory.newInstance().newDocumentBuilder()
    val document = builder.parse(inputStream)
    document.documentElement.normalize()

    val root = document.documentElement
    TraverseSvgForPaths(root, null, result)

    return result
}

@Composable
fun TraverseSvgForPaths(
    node: Node, currentGroupId: String?, result: MutableList<SVGData>
) {
    if (node.nodeType != Node.ELEMENT_NODE) return
    val element = node as Element

    var newGroupId = currentGroupId
    if (element.tagName == "g" && element.hasAttribute("id") ) {
        newGroupId = element.getAttribute("id")
    }

    if (element.tagName == "path" && newGroupId?.contains("seat", true) == true) {
        val pathData = element.getAttribute("d")
        val fillColor = extractFillColor(element)
        var color = fillColor.takeIf { it.isNotEmpty() }
            ?.let { Color(it.toColorInt()) } ?: Color.Transparent
        result.add(SVGData(newGroupId , pathData, color))
    }

    // Recurse into children
    val children = element.childNodes
    for (i in 0 until children.length) {
        TraverseSvgForPaths(children.item(i), newGroupId, result)
    }
}

fun extractFillColor(element: Element): String {
    // Try to extract from style="fill:#RRGGBB;"
    val style = element.getAttribute("style")
    val regex = Regex("""fill:\s*(#[0-9A-Fa-f]{6})""")
    val match = regex.find(style)
    if (match != null) {
        return match.groupValues[1]
    }
    return "#000000"
}
