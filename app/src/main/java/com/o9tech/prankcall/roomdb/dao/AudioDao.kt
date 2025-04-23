package com.o9tech.prankcall.roomdb.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.o9tech.prankcall.roomdb.databaseModel.AudioCallEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AudioDao {

    @Insert
    suspend fun insert(audio: AudioCallEntity)

    @Query("SELECT * FROM audio_call")
     fun getAllAudios(): Flow<List<AudioCallEntity>>

    @Query("SELECT * FROM audio_call WHERE id = :audioId LIMIT 1")
    suspend fun getAudioById(audioId: Int): AudioCallEntity?

    @Delete
    suspend fun deleteAudio(audio: AudioCallEntity)



}