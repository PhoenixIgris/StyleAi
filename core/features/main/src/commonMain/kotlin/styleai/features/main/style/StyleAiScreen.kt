package styleai.features.main.style

sealed class StyleAiScreen {
    data object Welcome : StyleAiScreen()
    data object Questionnaire : StyleAiScreen()
    data object Review : StyleAiScreen()
    data object Loading : StyleAiScreen()
    data object Result : StyleAiScreen()
    data object Error : StyleAiScreen()
}
