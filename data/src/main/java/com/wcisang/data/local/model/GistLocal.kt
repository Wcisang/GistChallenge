package com.wcisang.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "gist")
data class GistLocal(
    @PrimaryKey val uid: String,
    @ColumnInfo(name = "owner_name") val ownerName: String,
    @ColumnInfo(name = "owner_image") val ownerImage: String
)
