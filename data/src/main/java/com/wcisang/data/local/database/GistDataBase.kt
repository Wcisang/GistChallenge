package com.wcisang.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.wcisang.data.local.dao.GistDao
import com.wcisang.data.local.model.GistLocal

@Database(entities = [GistLocal::class], version = 1)
abstract class GistDataBase : RoomDatabase() {
    abstract fun gistDao(): GistDao
}
