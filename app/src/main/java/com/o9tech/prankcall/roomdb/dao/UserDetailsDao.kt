package com.o9tech.prankcall.roomdb.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.o9tech.prankcall.roomdb.databaseModel.UserDetailsEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface UserDetailsDao {
    @Insert
    suspend fun insertUserDetails(userDetails: UserDetailsEntity)

    @Query("SELECT * FROM user_details")
    fun getAllUserDetails(): Flow<List<UserDetailsEntity>>

    @Query("SELECT * FROM user_details WHERE id = :userId")
    fun getUserDetailsById(userId: Int): Flow<UserDetailsEntity>
}