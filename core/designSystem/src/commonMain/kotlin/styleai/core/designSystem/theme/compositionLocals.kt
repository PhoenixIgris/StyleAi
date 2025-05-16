package styleai.core.designSystem.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import styleai.core.designSystem.theme.componentColors.AppBarColors
import styleai.core.designSystem.theme.componentColors.ButtonColors
import styleai.core.designSystem.theme.componentColors.InputColors


internal val LocalStyleAiColors = compositionLocalOf<StyleAiColors> {
    error("StyleAiColors is not provided")
}

internal val LocalThemeMode = compositionLocalOf<ThemeMode> {
    error("ThemeMode is not provided")
}

internal val LocalUserThemeMode = compositionLocalOf<UserThemeMode> {
    error("UserThemeMode is not provided")
}

internal val LocalStyleAiTypography = compositionLocalOf<StyleAiTypography> {
    error("UserThemeMode is not provided")
}

object StyleAiTheme {
    val themeMode: ThemeMode
        @Composable
        @ReadOnlyComposable
        get() = LocalThemeMode.current

    val userTheme: UserThemeMode
        @Composable
        @ReadOnlyComposable
        get() = LocalUserThemeMode.current

    val colors: StyleAiColors
        @Composable
        @ReadOnlyComposable
        get() = LocalStyleAiColors.current

    val typography: StyleAiTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalStyleAiTypography.current

    val dimens = StyleAiDimens

    val shapes = Shapes

    val buttonColors = ButtonColors

    val appBarColors = AppBarColors

    val inputColors = InputColors
}
