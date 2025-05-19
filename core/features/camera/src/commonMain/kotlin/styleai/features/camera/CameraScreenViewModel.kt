package styleai.features.camera

import androidx.lifecycle.ViewModel
import org.jetbrains.compose.resources.StringResource
import styleai.core.resources.Res
import styleai.core.resources.loading
import styleai.core.ui.components.toast.ToastState
import styleai.core.ui.components.toast.toastState
import styleai.core.ui.delegates.StateManager
import styleai.core.ui.delegates.ViewModelStateManager
import styleai.features.camera.manager.SharedImage


data class CameraScreenState(
    val loading: Boolean = false,
    val loadingMessage: StringResource = Res.string.loading,
    val image: SharedImage? = null
)


class CameraScreenViewModel : ViewModel(), StateManager<CameraScreenState> by ViewModelStateManager(
    CameraScreenState()
), ToastState by toastState() {


    fun setImage(image: SharedImage) {
        updateState { copy(image = image) }
    }
}