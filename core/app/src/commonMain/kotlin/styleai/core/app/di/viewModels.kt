package styleai.core.app.di

import org.koin.dsl.module
import styleai.core.app.StyleAiUiViewModel
import styleai.core.data.repository.StadiumRepository
import styleai.features.camera.CameraScreenViewModel
import styleai.features.interactive_svg.InteractiveScreenViewModel
import styleai.features.main.MainHomeScreenContainerScreenViewModel

internal fun viewModelModule() = module {
    factory { StyleAiUiViewModel() }
    factory { MainHomeScreenContainerScreenViewModel() }
    factory { CameraScreenViewModel() }
    factory { InteractiveScreenViewModel(get<StadiumRepository>()) }
}