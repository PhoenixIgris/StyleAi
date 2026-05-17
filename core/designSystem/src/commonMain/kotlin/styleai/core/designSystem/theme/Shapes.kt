package styleai.core.designSystem.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.dp

object Shapes {
    val buttonPrimary = RoundedCornerShape(StyleAiRadius.button)
    val outlineInputField = RoundedCornerShape(StyleAiRadius.chip)
    val small = RoundedCornerShape(StyleAiDimens.size_1)
    val medium = RoundedCornerShape(StyleAiDimens.size_2)
    val large = RoundedCornerShape(StyleAiDimens.size_3)
    val chip = RoundedCornerShape(StyleAiRadius.chip)
    val button = RoundedCornerShape(StyleAiRadius.button)
    val card = RoundedCornerShape(StyleAiRadius.card)
    val screenCard = RoundedCornerShape(StyleAiRadius.screenCard)
}

object StyleAiRadius {
    val chip = 16.dp
    val button = 24.dp
    val card = 24.dp
    val screenCard = 28.dp
}
