package styleai.core.designSystem.theme.componentColors

import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import styleai.core.designSystem.theme.StyleAiTheme

object InputColors {
    val default: TextFieldColors
        @Composable
        get() =
            OutlinedTextFieldDefaults.colors(
                focusedBorderColor = StyleAiTheme.colors.primary
            )
}
