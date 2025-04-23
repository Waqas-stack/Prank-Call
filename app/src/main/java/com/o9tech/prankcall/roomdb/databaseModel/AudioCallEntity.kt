package com.o9tech.prankcall.roomdb.databaseModel

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "audio_call")
data class AudioCallEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val imageName: String,
    val audioPath: String
)
