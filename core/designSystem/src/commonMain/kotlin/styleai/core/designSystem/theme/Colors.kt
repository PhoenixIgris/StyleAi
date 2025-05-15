package styleai.core.designSystem.theme

import androidx.compose.ui.graphics.Color
import styleai.core.common.Platform
import styleai.core.common.platform


interface StyleAiColors {
    val background: Color // Main background color
    val onBackground: Color // Primary text color on background
    val onBackgroundSoft: Color // Secondary text color on background
    val onBackgroundSofter: Color // Tertiary text color on background

    val altBackground: Color // Alternative background (e.g., for dark mode contrast)
    val onAltBackground: Color // Text color on alternative background

    val primary: Color // Primary brand color (0xFF181143)
    val primarySoft: Color // Slightly lighter primary variant
    val primarySofter: Color // Even softer primary variant

    val onPrimary: Color // Text color on primary backgrounds

    val secondary: Color // Secondary brand color for accents
    val onSecondary: Color // Text color on secondary background

    val surface: Color // Surface color for cards and elements
    val onSurface: Color // Surface color for cards and elements

    val error: Color // Error color for failure states
    val errorBackground: Color // Background color for error states
    val warning: Color // Warning color for cautionary states
    val warningBackground: Color // Background color for warnings
    val success: Color // Success color for positive states
    val successBackground: Color // Background color for success messages

    val edge: Color // Edge separator color
    val shadow: Color // Shadow color for UI depth
    val embeddedFrame: Color // Background color for embedded elements

    companion object {
        val light: StyleAiColors = StyleAiLightColors
        val dark: StyleAiColors = StyleAiDarkColors
        val black: StyleAiColors = StyleAiDarkColors
        val hintGrey: Color = Color(0xFFAAAAAA)

    }
}

private object StyleAiLightColors : StyleAiColors {
    override val background = Color(0xFFF5F5FA) // Soft white with a tint
    override val onBackground = Color.Black // Primary color for contrast
    override val onBackgroundSoft = Color(0xFF4E4B66) // Muted text color
    override val onBackgroundSofter = Color(0xFF6E6A89) // Even softer text color

    override val altBackground = Color(0xFFFFFFFF) // Pure white alternative
    override val onAltBackground = Color(0xFF181143)

    override val primary = Color(0xFF181143)
    override val primarySoft = Color(0xFF3B3578)
    override val primarySofter = Color(0xFF5C5798)
    override val onPrimary = Color(0xFFFFFFFF)

    override val secondary = Color(0xFF51BA96) // Teal secondary color
    override val onSecondary = Color(0xFFFFFFFF)

    override val surface = Color(0xFFFFFFFF)
    override val onSurface = Color(0xFF181143)

    override val error = Color(0xFFD32F2F)
    override val errorBackground = Color(0xFFFFE5E5)
    override val warning = Color(0xFFF57C00)
    override val warningBackground = Color(0xFFFFF3CD)
    override val success = Color(0xFF388E3C)
    override val successBackground = Color(0xFFE8F5E9)

    override val edge: Color = Color(0xFF373746)

    override val embeddedFrame: Color = Color(0xFF0C0C0D)

    override val shadow: Color = when (platform()) {
        Platform.Android -> Color.LightGray
        Platform.IOS -> surface
    }
}

private object StyleAiDarkColors : StyleAiColors {
    override val background = Color(0xFFF5F5FA) // Soft white with a tint
    override val onBackground = Color.Black // Primary color for contrast
    override val onBackgroundSoft = Color(0xFF4E4B66) // Muted text color
    override val onBackgroundSofter = Color(0xFF6E6A89) // Even softer text color

    override val altBackground = Color(0xFFFFFFFF) // Pure white alternative
    override val onAltBackground = Color(0xFF181143)

    override val primary = Color(0xFF181143)
    override val primarySoft = Color(0xFF3B3578)
    override val primarySofter = Color(0xFF5C5798)
    override val onPrimary = Color(0xFFFFFFFF)

    override val secondary = Color(0xFF51BA96) // Teal secondary color
    override val onSecondary = Color(0xFFFFFFFF)

    override val surface = Color(0xFFFFFFFF)
    override val onSurface = Color(0xFF181143)

    override val error = Color(0xFFD32F2F)
    override val errorBackground = Color(0xFFFFE5E5)
    override val warning = Color(0xFFF57C00)
    override val warningBackground = Color(0xFFFFF3CD)
    override val success = Color(0xFF388E3C)
    override val successBackground = Color(0xFFE8F5E9)

    override val edge: Color = Color(0xFF373746)

    override val embeddedFrame: Color = Color(0xFF0C0C0D)

    override val shadow: Color = when (platform()) {
        Platform.Android -> Color.LightGray
        Platform.IOS -> surface
    }
}