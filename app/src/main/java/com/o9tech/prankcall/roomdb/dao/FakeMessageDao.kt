package com.o9tech.prankcall.roomdb.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.o9tech.prankcall.roomdb.databaseModel.MessageEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FakeMessageDao {
    @Insert
    suspend fun insertFakeMessage(messageEntity: MessageEntity)


    @Query("SELECT * FROM fake_message")
    fun getFakeMessage(): Flow<List<MessageEntity>>
}