package styleai.core.app.di

import org.koin.dsl.module
import kotlin.native.HiddenFromObjC

@HiddenFromObjC
fun appModule() = module {
    includes(
       viewModelModule()
    )
}
