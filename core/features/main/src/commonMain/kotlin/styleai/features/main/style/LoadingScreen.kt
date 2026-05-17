package styleai.features.main.style

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import styleai.core.designSystem.elements.StyleAiLoadingDots
import styleai.core.designSystem.elements.StyleAiScreenContainer
import styleai.core.designSystem.theme.StyleAiTheme
import styleai.core.resources.StyleAiAssets

@Composable
fun LoadingScreen(
    modifier: Modifier = Modifier,
) {
    StyleAiScreenContainer(
        modifier = modifier,
        scrollable = false,
        contentPadding = PaddingValues(StyleAiTheme.spacing.lg),
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                StyleAiCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(280.dp),
                    shape = StyleAiTheme.shapes.screenCard,
                    elevation = StyleAiTheme.shadows.card,
                    contentPadding = PaddingValues(StyleAiTheme.spacing.lg),
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center,
                    ) {
                        Image(
                            painter = painterResource(StyleAiAssets.Decorative.sparkles),
                            contentDescription = null,
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .size(52.dp)
                                .alpha(0.72f),
                        )
                        Image(
                            painter = painterResource(StyleAiAssets.Illustrations.aiLoadingCards),
                            contentDescription = "StyleAI loading illustration",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(StyleAiTheme.spacing.md),
                            contentScale = ContentScale.Fit,
                        )
                    }
                }

                Spacer(Modifier.height(StyleAiTheme.spacing.xl))

                Text(
                    text = "Creating your personalized style guide...",
                    modifier = Modifier.fillMaxWidth(),
                    style = StyleAiTheme.typography.screenTitle,
                    color = StyleAiTheme.colors.darkBrownText,
                    textAlign = TextAlign.Center,
                )

                Spacer(Modifier.height(StyleAiTheme.spacing.sm))

                Text(
                    text = "Analyzing your style preferences",
                    style = StyleAiTheme.typography.bodyText,
                    color = StyleAiTheme.colors.warmBrown,
                    textAlign = TextAlign.Center,
                )

                Spacer(Modifier.height(StyleAiTheme.spacing.lg))

                StyleAiLoadingDots()
            }
        }
    }
}
