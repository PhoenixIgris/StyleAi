package styleai.core.network.di

import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module
import styleai.core.network.baseHttpClient
import styleai.core.network.di.Named

internal actual fun platformNetworkModule(): Module = module {
    factory(named(Named.HttpClient.base)) {
        baseHttpClient()
    }
}
