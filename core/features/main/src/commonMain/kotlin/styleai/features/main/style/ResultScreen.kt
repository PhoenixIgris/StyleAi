package styleai.features.main.style

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.DrawableResource
import styleai.core.designSystem.elements.StyleAiCard
import styleai.core.designSystem.elements.StyleAiPrimaryButton
import styleai.core.designSystem.elements.StyleAiResultCard
import styleai.core.designSystem.elements.StyleAiScreenContainer
import styleai.core.designSystem.elements.StyleAiSecondaryButton
import styleai.core.designSystem.theme.StyleAiTheme
import styleai.core.models.style.StyleRecommendation
import styleai.core.resources.StyleAiAssets

@Composable
fun ResultScreen(
    recommendation: StyleRecommendation,
    onEditAnswers: () -> Unit,
    onGenerateAgain: () -> Unit,
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
        Text(
            text = "Your Style Recommendation",
            modifier = Modifier.fillMaxWidth(),
            style = StyleAiTheme.typography.screenTitle,
            color = StyleAiTheme.colors.darkBrownText,
            textAlign = TextAlign.Center,
        )

        Spacer(Modifier.height(StyleAiTheme.spacing.sm))

        Text(
            text = "Personalized based on your answers",
            modifier = Modifier.fillMaxWidth(),
            style = StyleAiTheme.typography.bodyText,
            color = StyleAiTheme.colors.warmBrown,
            textAlign = TextAlign.Center,
        )

        Spacer(Modifier.height(StyleAiTheme.spacing.lg))

        StyleAiResultCard(
            title = "Summary",
            body = recommendation.summary,
            icon = StyleAiAssets.Icons.aiSparkle,
        )
        Spacer(Modifier.height(StyleAiTheme.spacing.md))

        StyleAiResultCard(
            title = "Outfit Idea",
            body = recommendation.outfitIdea,
            icon = StyleAiAssets.Icons.outfitHanger,
        )
        Spacer(Modifier.height(StyleAiTheme.spacing.md))

        BestColorsCard(colors = recommendation.colorsToTry)
        Spacer(Modifier.height(StyleAiTheme.spacing.md))

        StyleAiResultCard(
            title = "Fit Tips",
            bullets = recommendation.fitTips,
            icon = StyleAiAssets.Icons.bodyMeasurement,
        )
        Spacer(Modifier.height(StyleAiTheme.spacing.md))

        StyleAiResultCard(
            title = "Accessories",
            bullets = recommendation.accessories,
            icon = StyleAiAssets.Icons.accessoriesEarrings,
        )
        Spacer(Modifier.height(StyleAiTheme.spacing.md))

        StyleAiResultCard(
            title = "Footwear",
            body = recommendation.footwear,
            icon = StyleAiAssets.Icons.shoesFootwear,
        )
        Spacer(Modifier.height(StyleAiTheme.spacing.md))

        StyleAiResultCard(
            title = "Hair and Grooming",
            body = recommendation.hairAndGrooming,
            icon = StyleAiAssets.Icons.faceShape,
        )
        Spacer(Modifier.height(StyleAiTheme.spacing.md))

        StyleAiResultCard(
            title = "Budget Tips",
            bullets = recommendation.budgetTips,
            icon = StyleAiAssets.Icons.piggyBank,
        )
        Spacer(Modifier.height(StyleAiTheme.spacing.md))

        StyleAiResultCard(
            title = "Confidence Boost",
            body = recommendation.confidenceBoost,
            icon = StyleAiAssets.Icons.magicWand,
        )

        Spacer(Modifier.height(StyleAiTheme.spacing.xl))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(StyleAiTheme.spacing.md),
        ) {
            StyleAiSecondaryButton(
                text = "Edit Answers",
                onClick = onEditAnswers,
                modifier = Modifier.weight(1f),
                fullWidth = false,
            )
            StyleAiPrimaryButton(
                text = "Generate Again",
                onClick = onGenerateAgain,
                modifier = Modifier.weight(1f),
                fullWidth = false,
                trailingIcon = StyleAiAssets.Icons.generateRefreshSparkle,
            )
        }
    }
}

@Composable
private fun BestColorsCard(
    colors: List<String>,
    modifier: Modifier = Modifier,
) {
    StyleAiCard(
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(StyleAiTheme.spacing.md),
    ) {
        ResultSectionTitle(
            title = "Best Colors",
            icon = StyleAiAssets.Icons.colorPalette,
        )

        Spacer(Modifier.height(StyleAiTheme.spacing.md))

        Column(
            verticalArrangement = Arrangement.spacedBy(StyleAiTheme.spacing.sm),
        ) {
            colors.chunked(2).forEach { rowColors ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(StyleAiTheme.spacing.sm),
                ) {
                    rowColors.forEach { color ->
                        ColorChip(
                            text = color,
                            modifier = Modifier.weight(1f),
                        )
                    }
                    if (rowColors.size == 1) {
                        Spacer(Modifier.weight(1f))
                    }
                }
            }
        }
    }
}

@Composable
private fun ResultSectionTitle(
    title: String,
    icon: DrawableResource?,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(StyleAiTheme.spacing.sm),
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(StyleAiTheme.colors.lightBeige, StyleAiTheme.shapes.chip),
            contentAlignment = Alignment.Center,
        ) {
            if (icon != null) {
                androidx.compose.material3.Icon(
                    painter = org.jetbrains.compose.resources.painterResource(icon),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    tint = androidx.compose.ui.graphics.Color.Unspecified,
                )
            }
        }
        Text(
            text = title,
            style = StyleAiTheme.typography.sectionTitle,
            color = StyleAiTheme.colors.darkBrownText,
        )
    }
}

@Composable
private fun ColorChip(
    text: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .background(StyleAiTheme.colors.blushPink, StyleAiTheme.shapes.chip)
            .padding(
                horizontal = StyleAiTheme.spacing.md,
                vertical = StyleAiTheme.spacing.sm,
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(StyleAiTheme.spacing.sm),
    ) {
        Box(
            modifier = Modifier
                .size(10.dp)
                .background(StyleAiTheme.colors.mutedGold, CircleShape),
        )
        Text(
            text = text,
            style = StyleAiTheme.typography.bodyText,
            color = StyleAiTheme.colors.warmBrown,
        )
    }
}
