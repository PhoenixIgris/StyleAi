package styleai.core.designSystem.theme.componentColors

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import styleai.core.designSystem.theme.StyleAiTheme

@OptIn(ExperimentalMaterial3Api::class)
object AppBarColors {
    val defaultTopAppBar: TopAppBarColors
        @Composable get() = TopAppBarDefaults.topAppBarColors(
            containerColor = StyleAiTheme.colors.background,
        )

    val transparent: TopAppBarColors
        @Composable get() = defaultTopAppBar.copy(containerColor = Color.Transparent)
}
