package com.o9tech.prankcall.roomdb.databaseModel

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "videos")
data class VideoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val videoPath: String
)


