package styleai.core.designSystem.elements

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import styleai.core.designSystem.theme.Shapes
import styleai.core.designSystem.theme.StyleAiTheme.typography
import styleai.core.designSystem.theme.componentColors.ButtonColors.default
import styleai.core.designSystem.theme.componentColors.ButtonColors.outlined

const val BUTTON_HEIGHT = 52

@Composable
fun DefaultButtonText(
    label: String, color: Color, textStyle: TextStyle = typography.label.LargeBold
) {
    Text(
        text = label,
        style = textStyle,
        color = color,
        modifier = Modifier.padding(horizontal = 12.dp)
    )
}

@Composable
fun DefaultButton(
    modifier: Modifier = Modifier.wrapContentSize(),
    label: String,
    color: ButtonColors = default,
    textStyle: TextStyle = typography.label.LargeBold,
    icon: DrawableResource? = null,
    enabled: Boolean = true,
    height: Int = BUTTON_HEIGHT,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier.height(height.dp),
        colors = color,
        onClick = onClick,
        enabled = enabled,
        shape = Shapes.small
    ) {
        if (icon != null) {
            Icon(
                painter = painterResource(icon),
                contentDescription = "",
                modifier = Modifier.width(width = 20.dp),
                tint = color.contentColor
            )
        }
        DefaultButtonText(label, color.contentColor)
    }
}

@Composable
fun DefaultOutLineButton(
    modifier: Modifier = Modifier.wrapContentSize(),
    color: ButtonColors = outlined,
    label: String,
    height: Int = BUTTON_HEIGHT,
    textStyle: TextStyle = typography.label.LargeBold,
    enabled: Boolean = true,
    icon: DrawableResource? = null,
    isTransparent: Boolean = false,
    onClick: () -> Unit
) {
    OutlinedButton(
        modifier = modifier.height(height.dp),
        onClick = onClick,
        shape = Shapes.small,
        enabled = enabled,
        colors = if (isTransparent) ButtonDefaults.outlinedButtonColors(Color.Transparent) else color
    ) {
        if (icon != null) {
            Icon(
                painter = painterResource(icon),
                contentDescription = "",
                modifier = Modifier.width(width = 20.dp),
                tint = color.contentColor
            )
        }
        DefaultButtonText(label, color.contentColor)
    }
}



