package de.fhe.ai.weemeal.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import de.fhe.ai.weemeal.local.entity.TagEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TagEntityDao {
    @Query("SELECT * FROM TagEntity")
    fun getAllAsFlow(): Flow<List<TagEntity>>

    @Query("SELECT * FROM TagEntity")
    suspend fun getAll(): List<TagEntity>

    @Query("SELECT * FROM TagEntity WHERE id = :id")
    suspend fun get(id: Long): TagEntity?

    @Query("SELECT * FROM TagEntity WHERE name LIKE '%' || :name || '%'")
    fun search(name: String?): Flow<List<TagEntity>>

    @Insert
    suspend fun insert(entity: TagEntity): Long

    @Update
    suspend fun update(entity: TagEntity)

    @Delete
    suspend fun delete(entity: TagEntity)

    @Delete
    suspend fun delete(vararg entities: TagEntity)

    @Query("DELETE FROM TagEntity")
    suspend fun deleteAll()
}
