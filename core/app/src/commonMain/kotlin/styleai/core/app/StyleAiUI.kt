package styleai.core.app

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import styleai.core.designSystem.theme.StyleAiTheme

@Composable
fun StyleAiUI() {
    StyleAiTheme {
        Box(Modifier.fillMaxSize()) {
            Text("Hello StyleAi")
        }
    }

}