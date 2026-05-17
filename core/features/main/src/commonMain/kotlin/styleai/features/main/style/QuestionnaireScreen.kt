package styleai.features.main.style

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.DrawableResource
import styleai.core.designSystem.elements.StyleAiChoiceChip
import styleai.core.designSystem.elements.StyleAiPrimaryButton
import styleai.core.designSystem.elements.StyleAiProgressHeader
import styleai.core.designSystem.elements.StyleAiSecondaryButton
import styleai.core.designSystem.theme.StyleAiTheme
import styleai.core.models.style.StyleQuestion
import styleai.core.resources.StyleAiAssets

@Composable
fun QuestionnaireScreen(
    currentQuestion: StyleQuestion,
    currentQuestionIndex: Int,
    totalQuestions: Int,
    selectedAnswer: String?,
    onAnswerSelected: (String) -> Unit,
    onBack: () -> Unit,
    onNext: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(StyleAiTheme.colors.creamBackground)
            .padding(WindowInsets.safeDrawing.asPaddingValues())
            .padding(
                PaddingValues(
                    horizontal = StyleAiTheme.spacing.lg,
                    vertical = StyleAiTheme.spacing.md,
                ),
            ),
    ) {
        StyleAiProgressHeader(
            currentStep = currentQuestionIndex + 1,
            totalSteps = totalQuestions,
            onBack = onBack,
        )

        Spacer(Modifier.height(StyleAiTheme.spacing.xl))

        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = currentQuestion.title,
                modifier = Modifier.fillMaxWidth(),
                style = StyleAiTheme.typography.screenTitle,
                color = StyleAiTheme.colors.darkBrownText,
                textAlign = TextAlign.Center,
            )

            Spacer(Modifier.height(StyleAiTheme.spacing.xl))

            currentQuestion.options.chunked(2).forEach { rowOptions ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(StyleAiTheme.spacing.md),
                ) {
                    rowOptions.forEach { option ->
                        StyleAiChoiceChip(
                            text = option,
                            selected = selectedAnswer == option,
                            onClick = { onAnswerSelected(option) },
                            icon = option.iconForQuestion(currentQuestion.id),
                            modifier = Modifier.weight(1f),
                        )
                    }
                    if (rowOptions.size == 1) {
                        Spacer(Modifier.weight(1f))
                    }
                }
                Spacer(Modifier.height(StyleAiTheme.spacing.md))
            }
        }

        Spacer(Modifier.height(StyleAiTheme.spacing.md))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(StyleAiTheme.spacing.md),
        ) {
            StyleAiSecondaryButton(
                text = "Back",
                onClick = onBack,
                modifier = Modifier.weight(1f),
                fullWidth = false,
            )
            StyleAiPrimaryButton(
                text = "Next",
                onClick = onNext,
                modifier = Modifier.weight(1f),
                enabled = selectedAnswer != null,
                fullWidth = false,
            )
        }
    }
}

private fun String.iconForQuestion(questionId: String): DrawableResource? {
    return if (questionId == "occasion") {
        when (this) {
            "Casual" -> StyleAiAssets.Icons.outfitHanger
            "Work" -> StyleAiAssets.Icons.workBriefcase
            "Wedding Guest" -> StyleAiAssets.Icons.weddingRing
            "Date Night" -> StyleAiAssets.Icons.dateHeart
            "Party" -> StyleAiAssets.Icons.partyDiscoball
            "Interview" -> StyleAiAssets.Icons.interviewPerson
            else -> null
        }
    } else {
        null
    }
}
