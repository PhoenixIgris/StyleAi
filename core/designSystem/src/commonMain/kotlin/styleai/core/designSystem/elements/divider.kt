package styleai.core.designSystem.elements

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import styleai.core.designSystem.theme.StyleAiTheme

@Composable
fun DefaultDivider(
    isHorizontal: Boolean = true, modifier: Modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
) {
    if (isHorizontal) {
        HorizontalDivider(
            modifier = modifier,
            thickness = 0.5.dp,
            color = StyleAiTheme.colors.edge
        )
    } else {
        VerticalDivider(
            modifier = modifier,
            thickness = 0.5.dp,
            color = StyleAiTheme.colors.edge
        )
    }
}
