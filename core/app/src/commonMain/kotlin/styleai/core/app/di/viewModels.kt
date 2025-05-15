package styleai.core.app.di

import org.koin.dsl.module
import styleai.core.app.StyleAiUiViewModel

internal fun viewModelModule() = module {
    factory { StyleAiUiViewModel() }
}