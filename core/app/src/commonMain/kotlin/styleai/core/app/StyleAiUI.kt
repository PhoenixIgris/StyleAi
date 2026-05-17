package styleai.core.app

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import org.koin.compose.koinInject
import styleai.core.designSystem.theme.StyleAiTheme
import styleai.core.ui.components.toast.ToastHost
import styleai.features.main.style.ErrorScreen
import styleai.features.main.style.LoadingScreen
import styleai.features.main.style.QuestionnaireScreen
import styleai.features.main.style.ResultScreen
import styleai.features.main.style.ReviewAnswersScreen
import styleai.features.main.style.StyleAiScreen
import styleai.features.main.style.StyleAiViewModel
import styleai.features.main.style.WelcomeScreen

@Composable
fun StyleAiUI() {
    StyleAiTheme {
        val viewModel = koinInject<StyleAiViewModel>()
        val state by viewModel.state.collectAsState()

        Box(Modifier.fillMaxSize()) {
            ToastHost {
                when (state.screen) {
                    StyleAiScreen.Welcome -> {
                        WelcomeScreen(
                            onStartClick = viewModel::startQuestionnaire,
                        )
                    }

                    StyleAiScreen.Questionnaire -> {
                        state.currentQuestion?.let { question ->
                            QuestionnaireScreen(
                                currentQuestion = question,
                                currentQuestionIndex = state.currentQuestionIndex,
                                totalQuestions = state.questions.size,
                                selectedAnswer = state.currentAnswer.takeIf { it.isNotBlank() },
                                onAnswerSelected = { option ->
                                    viewModel.selectAnswer(question.id, option)
                                },
                                onBack = viewModel::goBack,
                                onNext = viewModel::goNext,
                            )
                        } ?: WelcomeScreen(
                            onStartClick = viewModel::startQuestionnaire,
                        )
                    }

                    StyleAiScreen.Review -> {
                        ReviewAnswersScreen(
                            answers = state.questionnaireAnswers,
                            onNotesChanged = viewModel::updateNotes,
                            onGenerate = viewModel::generateStyleGuide,
                            onEditAnswers = viewModel::editAnswers,
                        )
                    }

                    StyleAiScreen.Loading -> {
                        LoadingScreen()
                    }

                    StyleAiScreen.Result -> {
                        state.result?.let { recommendation ->
                            ResultScreen(
                                recommendation = recommendation,
                                onEditAnswers = viewModel::goToReview,
                                onGenerateAgain = viewModel::generateAgain,
                            )
                        } ?: LoadingScreen()
                    }

                    StyleAiScreen.Error -> {
                        ErrorScreen(
                            technicalMessage = state.error,
                            onTryAgain = viewModel::generateAgain,
                            onEditAnswers = viewModel::goToReview,
                        )
                    }
                }
            }
        }
    }
}
