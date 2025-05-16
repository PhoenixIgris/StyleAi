package styleai.core.designSystem.theme.componentColors

import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import styleai.core.designSystem.theme.StyleAiTheme

object ButtonColors {
    val default: ButtonColors
        @Composable get() = ButtonDefaults.buttonColors(
            containerColor = StyleAiTheme.colors.primary, contentColor = Color.White
        )
    val error: ButtonColors
        @Composable get() = ButtonDefaults.buttonColors(
            containerColor = StyleAiTheme.colors.error, contentColor = Color.White
        )

    val outlined: ButtonColors
        @Composable get() = ButtonDefaults.outlinedButtonColors(
            StyleAiTheme.colors.onPrimary,
            StyleAiTheme.colors.primary,
            StyleAiTheme.colors.onPrimary,
            StyleAiTheme.colors.primarySofter,
        )

    val outlinedError: ButtonColors
        @Composable get() = ButtonDefaults.outlinedButtonColors(
            Color.Transparent,
            StyleAiTheme.colors.error,
            StyleAiTheme.colors.error,
            StyleAiTheme.colors.error,
        )
}
