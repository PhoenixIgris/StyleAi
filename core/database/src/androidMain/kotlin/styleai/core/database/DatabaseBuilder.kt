package styleai.core.database

import android.content.Context
import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import styleai.core.database.DatabaseConfig.DATABASE_NAME


fun getDatabaseBuilder(ctx: Context): RoomDB {
    val dbFile = ctx.getDatabasePath(DATABASE_NAME)
    return Room.databaseBuilder(ctx, RoomDB::class.java, dbFile.absolutePath).setDriver(BundledSQLiteDriver())
        .fallbackToDestructiveMigration(true).allowMainThreadQueries()
        .build()
}