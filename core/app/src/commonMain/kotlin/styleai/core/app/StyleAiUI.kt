package styleai.core.app

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import styleai.core.designSystem.theme.StyleAiTheme
import styleai.core.ui.components.toast.ToastHost

@Composable
fun StyleAiUI() {
    StyleAiTheme {
        val navigator = rememberNavController()
        Box(Modifier.fillMaxSize())
        {
            ToastHost {
            }
        }
    }
}