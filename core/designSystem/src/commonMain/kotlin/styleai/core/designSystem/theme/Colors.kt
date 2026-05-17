package styleai.core.designSystem.theme

import androidx.compose.ui.graphics.Color
import styleai.core.common.Platform
import styleai.core.common.platform


interface StyleAiColors {
    val creamBackground: Color
    val blushPink: Color
    val blushPinkDark: Color
    val mutedGold: Color
    val warmBrown: Color
    val darkBrownText: Color
    val lightBeige: Color
    val cardCream: Color
    val softBorder: Color
    val errorSoft: Color

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
    override val creamBackground = Color(0xFFFFF7EE)
    override val blushPink = Color(0xFFF7D6DA)
    override val blushPinkDark = Color(0xFFD98C86)
    override val mutedGold = Color(0xFFE0B26B)
    override val warmBrown = Color(0xFF6B4A3A)
    override val darkBrownText = Color(0xFF3B241C)
    override val lightBeige = Color(0xFFF5EDE3)
    override val cardCream = Color(0xFFFFFDFC)
    override val softBorder = Color(0xFFEAD9CF)
    override val errorSoft = Color(0xFFB85C5C)

    override val background = creamBackground
    override val onBackground = darkBrownText
    override val onBackgroundSoft = warmBrown
    override val onBackgroundSofter = Color(0xFF8A6A5D)

    override val altBackground = lightBeige
    override val onAltBackground = darkBrownText

    override val primary = warmBrown
    override val primarySoft = blushPinkDark
    override val primarySofter = blushPink
    override val onPrimary = cardCream

    override val secondary = mutedGold
    override val onSecondary = darkBrownText

    override val surface = cardCream
    override val onSurface = darkBrownText

    override val error = errorSoft
    override val errorBackground = Color(0xFFFFECE8)
    override val warning = mutedGold
    override val warningBackground = Color(0xFFFFF1D8)
    override val success = Color(0xFF7F8F5A)
    override val successBackground = Color(0xFFF3F5E8)

    override val edge: Color = softBorder

    override val embeddedFrame: Color = lightBeige

    override val shadow: Color = when (platform()) {
        Platform.Android -> Color(0x336B4A3A)
        Platform.IOS -> surface
    }
}

private object StyleAiDarkColors : StyleAiColors {
    override val creamBackground = StyleAiLightColors.creamBackground
    override val blushPink = StyleAiLightColors.blushPink
    override val blushPinkDark = StyleAiLightColors.blushPinkDark
    override val mutedGold = StyleAiLightColors.mutedGold
    override val warmBrown = StyleAiLightColors.warmBrown
    override val darkBrownText = StyleAiLightColors.darkBrownText
    override val lightBeige = StyleAiLightColors.lightBeige
    override val cardCream = StyleAiLightColors.cardCream
    override val softBorder = StyleAiLightColors.softBorder
    override val errorSoft = StyleAiLightColors.errorSoft

    override val background = StyleAiLightColors.background
    override val onBackground = StyleAiLightColors.onBackground
    override val onBackgroundSoft = StyleAiLightColors.onBackgroundSoft
    override val onBackgroundSofter = StyleAiLightColors.onBackgroundSofter
    override val altBackground = StyleAiLightColors.altBackground
    override val onAltBackground = StyleAiLightColors.onAltBackground
    override val primary = StyleAiLightColors.primary
    override val primarySoft = StyleAiLightColors.primarySoft
    override val primarySofter = StyleAiLightColors.primarySofter
    override val onPrimary = StyleAiLightColors.onPrimary
    override val secondary = StyleAiLightColors.secondary
    override val onSecondary = StyleAiLightColors.onSecondary
    override val surface = StyleAiLightColors.surface
    override val onSurface = StyleAiLightColors.onSurface
    override val error = StyleAiLightColors.error
    override val errorBackground = StyleAiLightColors.errorBackground
    override val warning = StyleAiLightColors.warning
    override val warningBackground = StyleAiLightColors.warningBackground
    override val success = StyleAiLightColors.success
    override val successBackground = StyleAiLightColors.successBackground
    override val edge = StyleAiLightColors.edge
    override val shadow = StyleAiLightColors.shadow
    override val embeddedFrame = StyleAiLightColors.embeddedFrame
}
