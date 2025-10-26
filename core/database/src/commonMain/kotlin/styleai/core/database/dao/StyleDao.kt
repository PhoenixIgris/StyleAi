package styleai.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import styleai.core.database.local.StyleEntity

@Dao
interface StyleDao {
    @Query("SELECT *  from StyleEntity")
    fun getTests(): Flow<List<StyleEntity>>

    @Query("DELETE FROM StyleEntity WHERE id NOT in (:testIds)")
    suspend fun deleteExceptIds(testIds: List<String>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAllSync(directoryEntities: List<StyleEntity>)

    @Transaction
    suspend fun replaceAllSync(tests: List<StyleEntity>) {
        deleteExceptIds(tests.map { it.id })
        saveAllSync(tests)
    }

    @Query("SELECT * FROM StyleEntity WHERE id = :testId")
    fun getTestById(testId: String): Flow<StyleEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(test: StyleEntity): Long

    @Update
    suspend fun updateTestItem(testItem: StyleEntity)


}