package styleai.core.app.di

import org.koin.dsl.module
import styleai.core.app.StyleAiUiViewModel
import styleai.features.main.MainHomeScreenContainerScreenViewModel

internal fun viewModelModule() = module {
    factory { StyleAiUiViewModel() }
    factory { MainHomeScreenContainerScreenViewModel() }
}