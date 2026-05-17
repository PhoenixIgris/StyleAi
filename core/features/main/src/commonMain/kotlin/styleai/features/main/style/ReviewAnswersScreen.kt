package styleai.features.main.style

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.DrawableResource
import styleai.core.designSystem.elements.StyleAiCard
import styleai.core.designSystem.elements.StyleAiPrimaryButton
import styleai.core.designSystem.elements.StyleAiScreenContainer
import styleai.core.designSystem.elements.StyleAiSecondaryButton
import styleai.core.designSystem.elements.StyleAiSummaryCard
import styleai.core.designSystem.theme.StyleAiTheme
import styleai.core.models.style.StyleQuestionnaireState
import styleai.core.resources.StyleAiAssets

@Composable
fun ReviewAnswersScreen(
    answers: StyleQuestionnaireState,
    onNotesChanged: (String) -> Unit,
    onGenerate: () -> Unit,
    onEditAnswers: () -> Unit,
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
            text = "Review Your Style Profile",
            modifier = Modifier.fillMaxWidth(),
            style = StyleAiTheme.typography.screenTitle,
            color = StyleAiTheme.colors.darkBrownText,
            textAlign = TextAlign.Center,
        )

        Spacer(Modifier.height(StyleAiTheme.spacing.lg))

        Column(
            verticalArrangement = Arrangement.spacedBy(StyleAiTheme.spacing.md),
        ) {
            reviewItems(answers).forEach { item ->
                StyleAiSummaryCard(
                    title = item.label,
                    value = item.value.ifBlank { "Not selected" },
                    icon = item.icon,
                )
            }
        }

        Spacer(Modifier.height(StyleAiTheme.spacing.lg))

        StyleAiCard(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(StyleAiTheme.spacing.md),
        ) {
            Text(
                text = "Anything else you want StyleAI to know?",
                style = StyleAiTheme.typography.sectionTitle,
                color = StyleAiTheme.colors.darkBrownText,
            )
            Spacer(Modifier.height(StyleAiTheme.spacing.sm))
            OutlinedTextField(
                value = answers.notes,
                onValueChange = onNotesChanged,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(132.dp),
                placeholder = {
                    Text(
                        text = "Example: I prefer modest outfits, comfortable shoes, or gold jewelry.",
                        style = StyleAiTheme.typography.bodyText,
                    )
                },
                textStyle = StyleAiTheme.typography.bodyText,
                shape = StyleAiTheme.shapes.card,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = StyleAiTheme.colors.blushPinkDark,
                    unfocusedBorderColor = StyleAiTheme.colors.softBorder,
                    focusedContainerColor = StyleAiTheme.colors.cardCream,
                    unfocusedContainerColor = StyleAiTheme.colors.cardCream,
                    cursorColor = StyleAiTheme.colors.warmBrown,
                    focusedTextColor = StyleAiTheme.colors.darkBrownText,
                    unfocusedTextColor = StyleAiTheme.colors.darkBrownText,
                ),
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Sentences,
                ),
                minLines = 4,
            )
        }

        Spacer(Modifier.height(StyleAiTheme.spacing.xl))

        StyleAiPrimaryButton(
            text = "Generate My Style Guide",
            onClick = onGenerate,
        )

        Spacer(Modifier.height(StyleAiTheme.spacing.md))

        StyleAiSecondaryButton(
            text = "Edit Answers",
            onClick = onEditAnswers,
        )
    }
}

private data class ReviewItem(
    val label: String,
    val value: String,
    val icon: DrawableResource?,
)

private fun reviewItems(answers: StyleQuestionnaireState): List<ReviewItem> {
    return listOf(
        ReviewItem(
            label = "Gender / Style Category",
            value = answers.genderStyleCategory,
            icon = StyleAiAssets.Icons.outfitHanger,
        ),
        ReviewItem(
            label = "Occasion",
            value = answers.occasion,
            icon = StyleAiAssets.Icons.occasionCalendarStar,
        ),
        ReviewItem(
            label = "Preferred Style",
            value = answers.preferredStyle,
            icon = StyleAiAssets.Icons.magicWand,
        ),
        ReviewItem(
            label = "Skin Tone / Palette",
            value = answers.skinTone,
            icon = StyleAiAssets.Icons.colorPalette,
        ),
        ReviewItem(
            label = "Face Shape",
            value = answers.faceShape,
            icon = StyleAiAssets.Icons.faceShape,
        ),
        ReviewItem(
            label = "Height Range",
            value = answers.heightRange,
            icon = StyleAiAssets.Icons.bodyMeasurement,
        ),
        ReviewItem(
            label = "Fit Preference",
            value = answers.fitPreference,
            icon = StyleAiAssets.Icons.bodyMeasurement,
        ),
        ReviewItem(
            label = "Comfort Level",
            value = answers.comfortLevel,
            icon = StyleAiAssets.Icons.aiSparkle,
        ),
        ReviewItem(
            label = "Budget",
            value = answers.budget,
            icon = StyleAiAssets.Icons.budgetPriceTag,
        ),
        ReviewItem(
            label = "Season",
            value = answers.season,
            icon = StyleAiAssets.Icons.occasionCalendarStar,
        ),
    )
}
