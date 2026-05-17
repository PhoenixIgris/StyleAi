package styleai.core.designSystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalRippleConfiguration
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RippleConfiguration
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StyleAiTheme(
    userThemeMode: UserThemeMode = UserThemeMode.System,
    content: @Composable () -> Unit
) {
    val isSystemInDarkTheme = isSystemInDarkTheme()

    val themeMode = remember(userThemeMode, isSystemInDarkTheme) {
        when (userThemeMode) {
            UserThemeMode.System ->
                if (isSystemInDarkTheme) {
                    ThemeMode.Dark
                } else {
                    ThemeMode.Light
                }

            UserThemeMode.Light -> ThemeMode.Light
            UserThemeMode.Dark -> ThemeMode.Dark
            UserThemeMode.Black -> ThemeMode.Black
        }
    }

    val colors = remember(themeMode) {
        when (themeMode) {
            ThemeMode.Light -> StyleAiColors.light
            ThemeMode.Dark -> StyleAiColors.dark
            ThemeMode.Black -> StyleAiColors.black
        }
    }

    val textSelectionColors = remember(colors) {
        TextSelectionColors(
            handleColor = colors.onBackground,
            backgroundColor = colors.blushPink,
        )
    }

    val materialColorScheme = remember(colors) {
        lightColorScheme(
            primary = colors.primary,
            onPrimary = colors.onPrimary,
            secondary = colors.secondary,
            onSecondary = colors.onSecondary,
            background = colors.background,
            onBackground = colors.onBackground,
            surface = colors.surface,
            onSurface = colors.onSurface,
            error = colors.error,
            onError = colors.onPrimary,
            outline = colors.softBorder,
        )
    }

    CompositionLocalProvider(
        LocalThemeMode provides themeMode,
        LocalUserThemeMode provides userThemeMode,
        LocalStyleAiColors provides colors,
        LocalStyleAiTypography provides StyleAiFontTypography,
    ) {
        //comment this for preview
        SyncOsTheme(StyleAiTheme.userTheme, StyleAiTheme.themeMode)
        val materialTypography = androidx.compose.material3.Typography(
            displayLarge = StyleAiTheme.typography.largeTitle,
            headlineLarge = StyleAiTheme.typography.screenTitle,
            titleMedium = StyleAiTheme.typography.sectionTitle,
            bodyMedium = StyleAiTheme.typography.bodyText,
            labelLarge = StyleAiTheme.typography.button,
            labelSmall = StyleAiTheme.typography.caption,
        )

        MaterialTheme(
            colorScheme = materialColorScheme,
            typography = materialTypography,
        ) {
            CompositionLocalProvider(
                LocalRippleConfiguration provides RippleConfiguration(
                    rippleAlpha = RippleAlpha(
                        pressedAlpha = 0.10f,
                        focusedAlpha = 0.12f,
                        draggedAlpha = 0.08f,
                        hoveredAlpha = 0.04f,
                    ),
                    color = colors.primary
                ),
                LocalTextSelectionColors provides textSelectionColors,
                LocalTextStyle provides StyleAiTheme.typography.body.MediumNormal,
            ) {
                content()
            }
        }
    }
}
