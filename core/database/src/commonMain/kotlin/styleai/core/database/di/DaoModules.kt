package styleai.core.database.di

import org.koin.core.module.Module
import org.koin.dsl.module
import styleai.core.database.RoomDB
import styleai.core.database.dao.StyleDao

fun daoModules(): Module = module {
    single<StyleDao> { get<RoomDB>().testDao() }
}