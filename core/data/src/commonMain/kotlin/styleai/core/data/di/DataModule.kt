package styleai.core.data.di

import org.koin.core.module.Module
import org.koin.dsl.module
import styleai.core.data.repository.FakeStyleRepository
import styleai.core.data.repository.StadiumRepository
import styleai.core.data.repository.StyleRepository
import styleai.core.database.dao.StadiumDao
import styleai.core.database.di.databaseModule
import styleai.core.datastore.di.dataStoreModule
import styleai.core.network.StyleAiRemoteApi
import styleai.core.network.di.networkModule

fun dataModule(): Module = module {
    includes(
        databaseModule(),
        networkModule(),
        dataStoreModule(),
    )
    single<StadiumRepository> {
        StadiumRepository(
            get<StadiumDao>(),
            get<StyleAiRemoteApi>()
        )
    }
    single<StyleRepository> {
        FakeStyleRepository()
    }
}

