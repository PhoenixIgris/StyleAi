package styleai.features.main


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.stringResource
import styleai.core.ui.components.toast.LocalToastManager
import styleai.core.ui.customview.LoadingDialog


@Composable
fun MainHomeContainerScreen(
    viewModel: MainHomeScreenContainerScreenViewModel,
) {
    val state by viewModel.state.collectAsState()

    val localToastManager = LocalToastManager.current

    LaunchedEffect(viewModel.toast) {
        viewModel.toast.collect { event ->
            event.actUpOn {
                localToastManager.post(it)
            }
        }
    }

    if (state.loading) {
        LoadingDialog(message = stringResource(state.loadingMessage))
    }

    Column(
        modifier = Modifier
            .fillMaxSize(), verticalArrangement = Arrangement.Top
    ) {


    }

}

