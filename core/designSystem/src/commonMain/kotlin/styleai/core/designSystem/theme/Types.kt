package styleai.core.designSystem.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp


val FontFamily.Companion.Archivo: FontFamily
    @Composable
    get() = FontFamily.Default

private val lineHeightStyle = LineHeightStyle(
    alignment = LineHeightStyle.Alignment.Center,
    trim = LineHeightStyle.Trim.None,
)

@Composable
private fun rememberTextStyle(
    fontSize: TextUnit,
    fontWeight: FontWeight,
    fontFamily: FontFamily = FontFamily.Archivo,
    letterSpacing: TextUnit = TextUnit.Unspecified,
): TextStyle {
    val colors = StyleAiTheme.colors

    return remember(colors) {
        TextStyle(
            fontSize = fontSize,
            fontFamily = fontFamily,
            fontWeight = fontWeight,
            lineHeightStyle = lineHeightStyle,
            color = colors.onBackground,
            letterSpacing = letterSpacing,
        )
    }
}

@Suppress("PropertyName")
interface StyleAiTypography {
    @get:Composable
    val largeTitle: TextStyle
    @get:Composable
    val screenTitle: TextStyle
    @get:Composable
    val sectionTitle: TextStyle
    @get:Composable
    val bodyText: TextStyle
    @get:Composable
    val caption: TextStyle
    @get:Composable
    val button: TextStyle

    val display: Display
    val heading: Heading
    val body: Body
    val label: Label

    interface Display {
        @get:Composable
        val SmallRegular: TextStyle
        @get:Composable
        val MediumRegular: TextStyle
        @get:Composable
        val LargeRegular: TextStyle

        @get:Composable
        val SmallSemiBold: TextStyle
        @get:Composable
        val MediumSemiBold: TextStyle
        @get:Composable
        val LargeSemiBold: TextStyle

        @get:Composable
        val SmallBold: TextStyle
        @get:Composable
        val MediumBold: TextStyle
        @get:Composable
        val LargeBold: TextStyle
    }


    interface Heading {
        @get:Composable
        val SmallRegular: TextStyle
        @get:Composable
        val MediumRegular: TextStyle
        @get:Composable
        val LargeRegular: TextStyle

        @get:Composable
        val SmallSemiBold: TextStyle
        @get:Composable
        val DefaultSemiBold: TextStyle
        @get:Composable
        val LargeSemiBold: TextStyle

        @get:Composable
        val SmallBold: TextStyle
        @get:Composable
        val DefaultBold: TextStyle
        @get:Composable
        val LargeBold: TextStyle
    }


    interface Body {
        @get:Composable
        val SmallRegular: TextStyle
        @get:Composable
        val MediumRegular: TextStyle
        @get:Composable
        val LargeRegular: TextStyle

        @get:Composable
        val SmallNormal: TextStyle
        @get:Composable
        val MediumNormal: TextStyle
        @get:Composable
        val LargeNormal: TextStyle

        @get:Composable
        val SmallSemiBold: TextStyle
        @get:Composable
        val DefaultSemiBold: TextStyle
        @get:Composable
        val LargeSemiBold: TextStyle

        @get:Composable
        val SmallBold: TextStyle
        @get:Composable
        val DefaultBold: TextStyle
        @get:Composable
        val LargeBold: TextStyle
    }

    interface Label {
        @get:Composable
        val SmallRegular: TextStyle
        @get:Composable
        val MediumRegular: TextStyle
        @get:Composable
        val LargeRegular: TextStyle

        @get:Composable
        val SmallNormal: TextStyle
        @get:Composable
        val MediumNormal: TextStyle
        @get:Composable
        val LargeNormal: TextStyle

        @get:Composable
        val SmallSemiBold: TextStyle
        @get:Composable
        val DefaultSemiBold: TextStyle
        @get:Composable
        val LargeSemiBold: TextStyle

        @get:Composable
        val SmallBold: TextStyle
        @get:Composable
        val DefaultBold: TextStyle
        @get:Composable
        val LargeBold: TextStyle
    }

}

internal object StyleAiFontTypography : StyleAiTypography {
    override val largeTitle @Composable get() = rememberTextStyle(34.sp, FontWeight.SemiBold)
    override val screenTitle @Composable get() = rememberTextStyle(26.sp, FontWeight.SemiBold)
    override val sectionTitle @Composable get() = rememberTextStyle(18.sp, FontWeight.SemiBold)
    override val bodyText @Composable get() = rememberTextStyle(15.sp, FontWeight.Normal)
    override val caption @Composable get() = rememberTextStyle(12.sp, FontWeight.Normal)
    override val button @Composable get() = rememberTextStyle(16.sp, FontWeight.Medium)

    override val display = object : StyleAiTypography.Display {
        override val SmallRegular @Composable get() = rememberTextStyle(18.sp, FontWeight.Normal)
        override val MediumRegular @Composable get() = rememberTextStyle(24.sp, FontWeight.Normal)
        override val LargeRegular @Composable get() = rememberTextStyle(32.sp, FontWeight.Normal)

        override val SmallSemiBold @Composable get() = rememberTextStyle(18.sp, FontWeight.SemiBold)
        override val MediumSemiBold
            @Composable get() = rememberTextStyle(
                24.sp,
                FontWeight.SemiBold
            )
        override val LargeSemiBold @Composable get() = rememberTextStyle(32.sp, FontWeight.SemiBold)

        override val SmallBold @Composable get() = rememberTextStyle(18.sp, FontWeight.Bold)
        override val MediumBold @Composable get() = rememberTextStyle(24.sp, FontWeight.Bold)
        override val LargeBold @Composable get() = rememberTextStyle(32.sp, FontWeight.Bold)
    }


    override val heading = object : StyleAiTypography.Heading {
        override val SmallRegular @Composable get() = rememberTextStyle(14.sp, FontWeight.Normal)
        override val MediumRegular @Composable get() = rememberTextStyle(16.sp, FontWeight.Normal)
        override val LargeRegular @Composable get() = rememberTextStyle(20.sp, FontWeight.Normal)

        override val SmallSemiBold @Composable get() = rememberTextStyle(14.sp, FontWeight.SemiBold)
        override val DefaultSemiBold
            @Composable get() = rememberTextStyle(
                16.sp,
                FontWeight.SemiBold
            )
        override val LargeSemiBold @Composable get() = rememberTextStyle(20.sp, FontWeight.SemiBold)

        override val SmallBold @Composable get() = rememberTextStyle(14.sp, FontWeight.Bold)
        override val DefaultBold @Composable get() = rememberTextStyle(16.sp, FontWeight.Bold)
        override val LargeBold @Composable get() = rememberTextStyle(20.sp, FontWeight.Bold)
    }

    override val body: StyleAiTypography.Body = object : StyleAiTypography.Body {
        override val SmallRegular @Composable get() = rememberTextStyle(12.sp, FontWeight.Normal)
        override val MediumRegular @Composable get() = rememberTextStyle(14.sp, FontWeight.Normal)
        override val LargeRegular @Composable get() = rememberTextStyle(16.sp, FontWeight.Normal)

        override val SmallNormal @Composable get() = SmallRegular
        override val MediumNormal @Composable get() = MediumRegular
        override val LargeNormal @Composable get() = LargeRegular

        override val SmallSemiBold @Composable get() = rememberTextStyle(12.sp, FontWeight.SemiBold)
        override val DefaultSemiBold
            @Composable get() = rememberTextStyle(
                14.sp,
                FontWeight.SemiBold
            )
        override val LargeSemiBold @Composable get() = rememberTextStyle(16.sp, FontWeight.SemiBold)

        override val SmallBold @Composable get() = rememberTextStyle(12.sp, FontWeight.Bold)
        override val DefaultBold @Composable get() = rememberTextStyle(14.sp, FontWeight.Bold)
        override val LargeBold @Composable get() = rememberTextStyle(16.sp, FontWeight.Bold)
    }
    override val label: StyleAiTypography.Label = object : StyleAiTypography.Label {
        override val SmallRegular @Composable get() = rememberTextStyle(12.sp, FontWeight.Normal)
        override val MediumRegular @Composable get() = rememberTextStyle(14.sp, FontWeight.Normal)
        override val LargeRegular @Composable get() = rememberTextStyle(16.sp, FontWeight.Normal)

        override val SmallNormal @Composable get() = SmallRegular
        override val MediumNormal @Composable get() = MediumRegular
        override val LargeNormal @Composable get() = LargeRegular

        override val SmallSemiBold @Composable get() = rememberTextStyle(12.sp, FontWeight.SemiBold)
        override val DefaultSemiBold
            @Composable get() = rememberTextStyle(
                14.sp,
                FontWeight.SemiBold
            )
        override val LargeSemiBold @Composable get() = rememberTextStyle(16.sp, FontWeight.SemiBold)

        override val SmallBold @Composable get() = rememberTextStyle(12.sp, FontWeight.Bold)
        override val DefaultBold @Composable get() = rememberTextStyle(14.sp, FontWeight.Bold)
        override val LargeBold @Composable get() = rememberTextStyle(16.sp, FontWeight.Bold)
    }

}
