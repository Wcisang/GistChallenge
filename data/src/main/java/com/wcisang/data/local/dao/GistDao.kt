package com.wcisang.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.wcisang.data.local.model.GistLocal
import kotlinx.coroutines.flow.Flow

@Dao
interface GistDao {

    @Query("SELECT * FROM gist")
    fun getAllGist(): Flow<List<GistLocal>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(vararg gists: GistLocal)

    @Delete
    suspend fun delete(gist: GistLocal)
}
