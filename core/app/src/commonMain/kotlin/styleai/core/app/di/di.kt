package styleai.core.app.di

import org.koin.dsl.module
import styleai.core.data.di.dataModule
import kotlin.native.HiddenFromObjC

@HiddenFromObjC
fun appModule() = module {
    includes(
       viewModelModule(), dataModule()
    )
}
