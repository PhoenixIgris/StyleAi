package styleai.features.main.style

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import styleai.core.designSystem.elements.StyleAiCard
import styleai.core.designSystem.elements.StyleAiPrimaryButton
import styleai.core.designSystem.elements.StyleAiScreenContainer
import styleai.core.designSystem.elements.StyleAiSecondaryButton
import styleai.core.designSystem.theme.StyleAiTheme
import styleai.core.resources.Res
import styleai.core.resources.ic_info_critical

@Composable
fun ErrorScreen(
    technicalMessage: String?,
    onTryAgain: () -> Unit,
    onEditAnswers: () -> Unit,
    modifier: Modifier = Modifier,
) {
    StyleAiScreenContainer(
        modifier = modifier,
        scrollable = false,
        contentPadding = PaddingValues(StyleAiTheme.spacing.lg),
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(Modifier.weight(1f))

            StyleAiCard(
                modifier = Modifier.fillMaxWidth(),
                backgroundColor = StyleAiTheme.colors.cardCream,
                borderColor = StyleAiTheme.colors.softBorder,
                elevation = StyleAiTheme.shadows.card,
                contentPadding = PaddingValues(StyleAiTheme.spacing.xl),
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.ic_info_critical),
                        contentDescription = null,
                        modifier = Modifier.size(48.dp),
                        tint = Color.Unspecified,
                    )

                    Spacer(Modifier.height(StyleAiTheme.spacing.lg))

                    Text(
                        text = "Something went wrong",
                        style = StyleAiTheme.typography.screenTitle,
                        color = StyleAiTheme.colors.darkBrownText,
                        textAlign = TextAlign.Center,
                    )

                    Spacer(Modifier.height(StyleAiTheme.spacing.sm))

                    Text(
                        text = "We couldn't generate your style guide. Please try again.",
                        style = StyleAiTheme.typography.bodyText,
                        color = StyleAiTheme.colors.warmBrown,
                        textAlign = TextAlign.Center,
                    )

                    if (!technicalMessage.isNullOrBlank()) {
                        Spacer(Modifier.height(StyleAiTheme.spacing.md))
                        Text(
                            text = technicalMessage,
                            style = StyleAiTheme.typography.caption,
                            color = StyleAiTheme.colors.errorSoft,
                            textAlign = TextAlign.Center,
                        )
                    }
                }
            }

            Spacer(Modifier.weight(1f))

            StyleAiPrimaryButton(
                text = "Try Again",
                onClick = onTryAgain,
            )

            Spacer(Modifier.height(StyleAiTheme.spacing.md))

            StyleAiSecondaryButton(
                text = "Edit Answers",
                onClick = onEditAnswers,
            )
        }
    }
}
