package styleai.core.database.di

import org.koin.dsl.module
import styleai.core.database.RoomDB
import styleai.core.database.getDatabaseBuilder


actual fun databaseModule() = module {
    single<RoomDB> { getDatabaseBuilder() }
    includes(daoModules())
}