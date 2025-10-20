package styleai.core.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import styleai.core.database.DatabaseConfig.DATABASE_VERSION
import styleai.core.database.dao.StadiumDao
import styleai.core.database.dao.StyleDao
import styleai.core.models.local.StadiumEntity
import styleai.core.models.local.StyleEntity


@Database(
    entities = [
        StyleEntity::class,
        StadiumEntity::class
    ],
    version = DATABASE_VERSION,
    exportSchema = false
)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class RoomDB : RoomDatabase() {
    abstract fun testDao(): StyleDao
    abstract fun stadiumDao(): StadiumDao
}

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<RoomDB> {
    override fun initialize(): RoomDB
}
