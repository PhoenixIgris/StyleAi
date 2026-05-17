package styleai.core.app

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.koin.compose.koinInject
import styleai.core.designSystem.theme.StyleAiTheme
import styleai.core.ui.components.toast.ToastHost
import styleai.features.main.style.StyleAiViewModel
import styleai.features.main.style.WelcomeScreen

@Composable
fun StyleAiUI() {
    StyleAiTheme {
        val viewModel = koinInject<StyleAiViewModel>()

        Box(Modifier.fillMaxSize()) {
            ToastHost {
                WelcomeScreen(
                    onStartClick = viewModel::startQuestionnaire,
                )
            }
        }
    }
}
