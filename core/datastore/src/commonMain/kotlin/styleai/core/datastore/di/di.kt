package styleai.core.datastore.di

import org.koin.dsl.module
import styleai.core.datastore.DataStore

fun dataStoreModule() = module {
    single {
        DataStore()
    }
}