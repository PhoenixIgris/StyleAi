package styleai.core.ui.delegates

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlin.native.HiddenFromObjC

sealed interface StyleAiState<T> : StateFlow<T>

internal class StyleAiStateImpl<T>(
    delegate: MutableStateFlow<T>,
) : MutableStateFlow<T> by delegate, StyleAiState<T>

/**
 * Creates a new [StyleAiState] which is a [MutableStateFlow] but restricts the update to [ViewModel] scope
 */
@HiddenFromObjC
fun <T> stateOf(initial: T): StyleAiState<T> = StyleAiStateImpl(MutableStateFlow(initial))

@HiddenFromObjC
fun <T> StyleAiState<T>.update(block: T.() -> T) {
    (this as StyleAiStateImpl<T>).value = block(value)
}

@HiddenFromObjC
fun <T> StyleAiState<T>.setValue(value: T) {
    (this as StyleAiStateImpl<T>).value = value
}

fun <T> MutableStateFlow<T>.setValue(value: T) {
    this.value = value
}
