package styleai.core.database.di

import android.content.Context
import org.koin.dsl.module
import styleai.core.database.RoomDB
import styleai.core.database.getDatabaseBuilder


actual fun databaseModule() = module {
    single<RoomDB> { getDatabaseBuilder(get<Context>()) }
    includes(daoModules())
}