package styleai.core.ui.components.toast.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.union
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import styleai.core.ui.components.toast.Toast
import styleai.core.ui.components.toast.typeColor
import styleai.core.designSystem.theme.StyleAiTheme.colors
import styleai.core.designSystem.theme.StyleAiTheme.dimens
import styleai.core.designSystem.theme.StyleAiTheme.typography
import styleai.core.resources.getMessage
import styleai.core.resources.res

@Composable
internal fun ToastView(toast: Toast, onToastHide: (Toast) -> Unit) {
    LaunchedEffect(toast) {
        delay(toast.duration)
        onToastHide(toast)
    }

    Row(
        modifier = Modifier
            .windowInsetsPadding(WindowInsets.ime.union(WindowInsets.navigationBars))
            .shadow(
                elevation = 12.dp,
                shape = RoundedCornerShape(dimens.size_2),
                spotColor = colors.shadow,
                ambientColor = Color.Transparent,
            )
            .background(colors.surface, RoundedCornerShape(dimens.size_2))
            .heightIn(min = dimens.size_11)
            .padding(horizontal = dimens.size_4, vertical = dimens.size_2),
        horizontalArrangement = Arrangement.spacedBy(dimens.size_2),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        toast.icon?.also { icon ->
            Icon(icon, toast.message, tint = toast.typeColor)
        }

        toast.textIcon?.also {
            Text(it, style = typography.body.MediumNormal)
        }

        Text(
            toast.message?.getMessage ?: toast.messageRes?.res ?: "",
            style = typography.body.MediumNormal,
            color = toast.typeColor,
        )
    }
}
