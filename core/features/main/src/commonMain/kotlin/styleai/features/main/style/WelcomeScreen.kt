package styleai.features.main.style

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import styleai.core.designSystem.elements.StyleAiCard
import styleai.core.designSystem.elements.StyleAiPrimaryButton
import styleai.core.designSystem.elements.StyleAiScreenContainer
import styleai.core.designSystem.theme.StyleAiTheme
import styleai.core.resources.StyleAiAssets

@Composable
fun WelcomeScreen(
    onStartClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    StyleAiScreenContainer(
        modifier = modifier,
        scrollable = true,
        contentPadding = PaddingValues(
            horizontal = StyleAiTheme.spacing.lg,
            vertical = StyleAiTheme.spacing.xl,
        ),
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Spacer(Modifier.height(StyleAiTheme.spacing.sm))

            Box(contentAlignment = Alignment.TopEnd) {
                Text(
                    text = "StyleAI",
                    style = StyleAiTheme.typography.largeTitle,
                    color = StyleAiTheme.colors.darkBrownText,
                    textAlign = TextAlign.Center,
                )
                Image(
                    painter = painterResource(StyleAiAssets.Decorative.sparkles),
                    contentDescription = null,
                    modifier = Modifier
                        .size(28.dp)
                        .alpha(0.9f),
                )
            }

            Spacer(Modifier.height(StyleAiTheme.spacing.sm))

            Text(
                text = "Personal styling recommendations\npowered by AI",
                style = StyleAiTheme.typography.bodyText,
                color = StyleAiTheme.colors.warmBrown,
                textAlign = TextAlign.Center,
            )

            Spacer(Modifier.height(StyleAiTheme.spacing.xl))

            StyleAiCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 300.dp, max = 360.dp)
                    .aspectRatio(0.95f),
                shape = StyleAiTheme.shapes.screenCard,
                backgroundColor = StyleAiTheme.colors.cardCream,
                borderColor = StyleAiTheme.colors.softBorder,
                elevation = StyleAiTheme.shadows.screenCard,
                contentPadding = PaddingValues(StyleAiTheme.spacing.md),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentAlignment = Alignment.Center,
                ) {
                    Image(
                        painter = painterResource(StyleAiAssets.Decorative.sparkles),
                        contentDescription = null,
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(StyleAiTheme.spacing.sm)
                            .size(42.dp)
                            .alpha(0.68f),
                    )
                    Image(
                        painter = painterResource(StyleAiAssets.Illustrations.welcomeOutfitScene),
                        contentDescription = "StyleAI outfit illustration",
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(
                                horizontal = StyleAiTheme.spacing.md,
                                vertical = StyleAiTheme.spacing.lg,
                            ),
                        contentScale = ContentScale.Fit,
                    )
                }
            }

            Spacer(Modifier.height(StyleAiTheme.spacing.xl))

            Text(
                text = "Answer a few questions and get a personalized style guide.",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = StyleAiTheme.spacing.md),
                style = StyleAiTheme.typography.bodyText,
                color = StyleAiTheme.colors.warmBrown,
                textAlign = TextAlign.Center,
            )

            Spacer(Modifier.height(StyleAiTheme.spacing.lg))

            StyleAiPrimaryButton(
                text = "Start Styling",
                onClick = onStartClick,
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}
