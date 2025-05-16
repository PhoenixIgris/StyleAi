package styleai.core.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import styleai.core.database.DatabaseConfig.DATABASE_VERSION
import styleai.core.database.dao.StyleDao
import styleai.core.database.model.StyleEntity


@Database(
    entities = [
        StyleEntity::class
    ],
    version = DATABASE_VERSION,
    exportSchema = true
)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class RoomDB : RoomDatabase() {
    abstract fun testDao(): StyleDao
}

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<RoomDB> {
    override fun initialize(): RoomDB
}
