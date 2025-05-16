package styleai.core.network.di

import styleai.core.network.di.Named.HttpClient
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module
import styleai.core.network.baseHttpClient

internal actual fun platformNetworkModule(): Module = module {
    factory(named(HttpClient.base)) {
        baseHttpClient()
    }
}
