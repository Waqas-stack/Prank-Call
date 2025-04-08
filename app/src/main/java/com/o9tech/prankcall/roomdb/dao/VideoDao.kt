package com.o9tech.prankcall.roomdb.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.o9tech.prankcall.roomdb.databaseModel.VideoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface VideoDao {

    @Insert
    suspend fun insert(video: VideoEntity)

    @Query("SELECT * FROM videos")
    fun getAllVideos(): Flow<List<VideoEntity>>

    @Query("SELECT * FROM videos WHERE id = :videoId LIMIT 1")
    suspend fun getVideoById(videoId: Int): VideoEntity?

    @Delete
    suspend fun deleteVideo(video: VideoEntity)
}
