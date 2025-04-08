package com.o9tech.prankcall.roomdb.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.o9tech.prankcall.roomdb.databaseModel.MessageEntity
import com.o9tech.prankcall.roomdb.databaseModel.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert
    suspend fun insertUser(user: UserEntity)


    @Query("SELECT * FROM user_table")
     fun getAllUsers(): Flow<List<UserEntity>>





}