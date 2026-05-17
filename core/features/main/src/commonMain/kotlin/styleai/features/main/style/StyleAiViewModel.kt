package styleai.features.main.style

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import styleai.core.data.repository.StyleRepository
import styleai.core.models.style.StyleQuestion
import styleai.core.models.style.StyleQuestionnaireState
import styleai.core.models.style.StyleQuestions
import styleai.core.models.style.StyleRecommendation
import styleai.core.ui.delegates.StateManager
import styleai.core.ui.delegates.ViewModelStateManager

data class StyleAiViewState(
    val screen: StyleAiScreen = StyleAiScreen.Welcome,
    val questions: List<StyleQuestion> = StyleQuestions.all,
    val questionnaireAnswers: StyleQuestionnaireState = StyleQuestionnaireState(),
    val loading: Boolean = false,
    val result: StyleRecommendation? = null,
    val error: String? = null,
) {
    val currentQuestionIndex: Int = questionnaireAnswers.currentQuestionIndex
    val currentQuestion: StyleQuestion? = questions.getOrNull(currentQuestionIndex)
    val currentAnswer: String
        get() = currentQuestion?.let { questionnaireAnswers.answerFor(it.id) }.orEmpty()
    val canGoNext: Boolean
        get() = currentAnswer.isNotBlank()
    val isLastQuestion: Boolean
        get() = currentQuestionIndex == questions.lastIndex
}

class StyleAiViewModel(
    private val styleRepository: StyleRepository,
) : ViewModel(), StateManager<StyleAiViewState> by ViewModelStateManager(StyleAiViewState()) {

    fun startQuestionnaire() {
        updateState {
            copy(
                screen = StyleAiScreen.Questionnaire,
                questionnaireAnswers = StyleQuestionnaireState(),
                loading = false,
                result = null,
                error = null,
            )
        }
    }

    fun selectAnswer(questionId: String, option: String) {
        updateState {
            copy(questionnaireAnswers = questionnaireAnswers.withAnswer(questionId, option))
        }
    }

    fun goNext() {
        updateState {
            if (!canGoNext) {
                this
            } else if (isLastQuestion) {
                copy(screen = StyleAiScreen.Review)
            } else {
                copy(
                    screen = StyleAiScreen.Questionnaire,
                    questionnaireAnswers = questionnaireAnswers.copy(
                        currentQuestionIndex = currentQuestionIndex + 1,
                    ),
                )
            }
        }
    }

    fun goBack() {
        updateState {
            when (screen) {
                StyleAiScreen.Questionnaire -> {
                    if (currentQuestionIndex == 0) {
                        copy(screen = StyleAiScreen.Welcome)
                    } else {
                        copy(
                            questionnaireAnswers = questionnaireAnswers.copy(
                                currentQuestionIndex = currentQuestionIndex - 1,
                            ),
                        )
                    }
                }

                StyleAiScreen.Review -> copy(screen = StyleAiScreen.Questionnaire)
                StyleAiScreen.Result -> copy(screen = StyleAiScreen.Review)
                StyleAiScreen.Error -> copy(screen = StyleAiScreen.Review, error = null)
                StyleAiScreen.Loading -> this
                StyleAiScreen.Welcome -> this
            }
        }
    }

    fun updateNotes(notes: String) {
        updateState {
            copy(questionnaireAnswers = questionnaireAnswers.copy(notes = notes))
        }
    }

    fun goToReview() {
        updateState {
            if (questionnaireAnswers.isComplete) {
                copy(screen = StyleAiScreen.Review)
            } else {
                copy(screen = StyleAiScreen.Questionnaire)
            }
        }
    }

    fun editAnswers() {
        updateState {
            copy(
                screen = StyleAiScreen.Questionnaire,
                error = null,
                loading = false,
            )
        }
    }

    fun generateStyleGuide() {
        val request = state.value.questionnaireAnswers.toRequest()
        updateState {
            copy(
                screen = StyleAiScreen.Loading,
                loading = true,
                error = null,
            )
        }

        viewModelScope.launch {
            val recommendationResult = styleRepository.generateRecommendation(request)
            updateState {
                recommendationResult.fold(
                    onSuccess = { recommendation ->
                        copy(
                            screen = StyleAiScreen.Result,
                            loading = false,
                            result = recommendation,
                            error = null,
                        )
                    },
                    onFailure = { throwable ->
                        copy(
                            screen = StyleAiScreen.Error,
                            loading = false,
                            error = throwable.message ?: "Unable to generate your style guide.",
                        )
                    },
                )
            }
        }
    }

    fun generateAgain() {
        generateStyleGuide()
    }

    fun clearError() {
        updateState {
            copy(
                screen = if (result == null) StyleAiScreen.Review else StyleAiScreen.Result,
                loading = false,
                error = null,
            )
        }
    }
}

private fun StyleQuestionnaireState.answerFor(questionId: String): String {
    return when (questionId) {
        "genderStyleCategory" -> genderStyleCategory
        "occasion" -> occasion
        "preferredStyle" -> preferredStyle
        "skinTone" -> skinTone
        "faceShape" -> faceShape
        "heightRange" -> heightRange
        "fitPreference" -> fitPreference
        "comfortLevel" -> comfortLevel
        "budget" -> budget
        "season" -> season
        else -> ""
    }
}

private fun StyleQuestionnaireState.withAnswer(
    questionId: String,
    option: String,
): StyleQuestionnaireState {
    return when (questionId) {
        "genderStyleCategory" -> copy(genderStyleCategory = option)
        "occasion" -> copy(occasion = option)
        "preferredStyle" -> copy(preferredStyle = option)
        "skinTone" -> copy(skinTone = option)
        "faceShape" -> copy(faceShape = option)
        "heightRange" -> copy(heightRange = option)
        "fitPreference" -> copy(fitPreference = option)
        "comfortLevel" -> copy(comfortLevel = option)
        "budget" -> copy(budget = option)
        "season" -> copy(season = option)
        else -> this
    }
}
