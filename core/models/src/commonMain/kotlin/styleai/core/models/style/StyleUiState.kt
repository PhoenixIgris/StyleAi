package styleai.core.models.style

import kotlinx.serialization.Serializable

@Serializable
sealed class StyleUiState {
    @Serializable
    data object Welcome : StyleUiState()

    @Serializable
    data class Questionnaire(
        val questionnaireState: StyleQuestionnaireState = StyleQuestionnaireState(),
    ) : StyleUiState()

    @Serializable
    data class Review(
        val questionnaireState: StyleQuestionnaireState,
    ) : StyleUiState()

    @Serializable
    data object Loading : StyleUiState()

    @Serializable
    data class RecommendationResult(
        val recommendation: StyleRecommendation,
    ) : StyleUiState()

    @Serializable
    data class Error(
        val message: String,
    ) : StyleUiState()
}
