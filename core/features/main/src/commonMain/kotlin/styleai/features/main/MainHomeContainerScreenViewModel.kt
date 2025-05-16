package styleai.features.main

import androidx.lifecycle.ViewModel
import org.jetbrains.compose.resources.StringResource
import styleai.core.resources.Res
import styleai.core.resources.loading
import styleai.core.ui.components.toast.ToastState
import styleai.core.ui.components.toast.toastState
import styleai.core.ui.delegates.StateManager
import styleai.core.ui.delegates.ViewModelStateManager


data class MainHomeScreenContainerScreenState(
    val loading: Boolean = false,
    val loadingMessage: StringResource = Res.string.loading)


class MainHomeScreenContainerScreenViewModel(
) : ViewModel(), StateManager<MainHomeScreenContainerScreenState> by ViewModelStateManager(
    MainHomeScreenContainerScreenState()
), ToastState by toastState() {


}