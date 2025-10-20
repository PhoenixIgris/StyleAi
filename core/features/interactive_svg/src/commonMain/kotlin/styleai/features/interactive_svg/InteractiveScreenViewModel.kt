package styleai.features.interactive_svg

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.kittinunf.result.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.StringResource
import styleai.core.data.repository.StadiumRepository
import styleai.core.resources.Res
import styleai.core.resources.loading
import styleai.core.ui.components.toast.ToastState
import styleai.core.ui.components.toast.toastMessage
import styleai.core.ui.components.toast.toastState
import styleai.core.ui.delegates.StateManager
import styleai.core.ui.delegates.ViewModelStateManager


data class InteractiveScreenState(
    val loading: Boolean = false,
    val loadingMessage: StringResource = Res.string.loading,

    )


class InteractiveScreenViewModel(
    private val stadiumRepository: StadiumRepository
) : ViewModel(), StateManager<InteractiveScreenState> by ViewModelStateManager(
    InteractiveScreenState()
), ToastState by toastState() {


    private val _imageList = MutableStateFlow<List<String>?>(null)
    val imageList = _imageList.asStateFlow()

    init {
        viewModelScope.launch {
            when (val result = stadiumRepository.refreshStadiumData()) {
                is Result.Failure<*> -> {
                    toastMessage(result.error.message)
                }

                is Result.Success<*> -> {
                    stadiumRepository.getStadiumDataList().collectLatest {
                        _imageList.value = it.map { it.imageUrl }
                    }
                }
            }
        }

    }
}