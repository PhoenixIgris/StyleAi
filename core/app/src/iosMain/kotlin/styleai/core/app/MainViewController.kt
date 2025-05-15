package styleai.core.app

import androidx.compose.ui.window.ComposeUIViewController
import org.koin.core.context.startKoin
import styleai.core.app.di.appModule

@Suppress("unused", "FunctionName")
fun MainViewController() = ComposeUIViewController(
    configure = {
        startKoin {
            modules(appModule())
        }
    }
) {
    onStyleAiStarted()
    StyleAiUI()
}

