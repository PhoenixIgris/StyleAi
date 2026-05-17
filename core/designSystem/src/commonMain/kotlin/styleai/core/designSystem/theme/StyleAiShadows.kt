package styleai.core.designSystem.theme

import androidx.compose.material3.CardElevation
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

object StyleAiShadows {
    val flat = 0.dp
    val soft = 2.dp
    val card = 6.dp
    val screenCard = 10.dp

    val cardElevation: CardElevation
        @Composable get() = CardDefaults.cardElevation(defaultElevation = card)

    val screenCardElevation: CardElevation
        @Composable get() = CardDefaults.cardElevation(defaultElevation = screenCard)
}
