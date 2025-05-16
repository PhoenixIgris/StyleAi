package styleai.core.ui.components.toast

import androidx.compose.ui.graphics.vector.ImageVector
import org.jetbrains.compose.resources.StringResource
import styleai.core.ui.SingleEvent
import styleai.core.ui.delegates.StyleAiState
import styleai.core.ui.delegates.setValue
import styleai.core.ui.delegates.stateOf
import styleai.core.ui.toEvent

interface ToastState {
    val toast: StyleAiState<SingleEvent<Toast>>
}

fun toastState(): ToastState = object : ToastState {
    override val toast: StyleAiState<SingleEvent<Toast>> = stateOf(SingleEvent())
}

fun ToastState.toastMessage(
    message: String?,
    icon: ImageVector? = null,
    textIcon: String? = null,
    duration: Toast.Duration = Toast.Duration.SHORT,
    messageRes: StringResource? = null
) = toast.setValue(
    Toast(
        message,
        Toast.Type.Message,
        duration.value,
        icon,
        textIcon,
        messageRes
    ).toEvent()
)

fun ToastState.toastAlert(
    message: String?,
    icon: ImageVector? = null,
    textIcon: String? = null,
    duration: Toast.Duration = Toast.Duration.SHORT,
    messageRes: StringResource? = null
) = toast.setValue(
    Toast(
        message,
        Toast.Type.Alert,
        duration.value,
        icon,
        textIcon,
        messageRes
    ).toEvent()
)

fun ToastState.toastError(
    message: String?,
    icon: ImageVector? = null,
    textIcon: String? = null,
    duration: Toast.Duration = Toast.Duration.SHORT,
    messageRes: StringResource? = null
) = toast.setValue(
    (Toast(
        message,
        Toast.Type.Error,
        duration.value,
        icon,
        textIcon,
        messageRes
    ).toEvent())
)
