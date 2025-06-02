package com.o9tech.prankcall.repositry

import com.o9tech.prankcall.roomdb.databaseModel.MessageEntity
import com.o9tech.prankcall.roomdb.databaseModel.UserDetailsEntity
import com.o9tech.prankcall.roomdb.databaseModel.UserEntity
import com.o9tech.prankcall.roomdb.databaseModel.VideoEntity
import com.o9tech.prankcall.roomdb.db.UserDatabase
import com.o9tech.prankcall.utils.AppConstant.getVideoPathFromAssets
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class MainRepo @Inject constructor(
    private val userDatabase: UserDatabase
) {
    suspend fun insertUser(user: UserEntity) = userDatabase.userDao().insertUser(user)
    suspend fun InsertFakeMessage(messageEntity: MessageEntity) = userDatabase.fakeMessageDao().insertFakeMessage(messageEntity)


    suspend fun insertVideo(videoEntity: VideoEntity) = userDatabase.videoDao().insert(videoEntity)
     fun getAllVideos(): Flow<List<VideoEntity>> = userDatabase.videoDao().getAllVideos()

    fun getUsers(): Flow<List<UserEntity>> = userDatabase.userDao().getAllUsers()

    fun getFakeMessage(): Flow<List<MessageEntity>> = userDatabase.fakeMessageDao().getFakeMessage()

    suspend fun insertUserDetails(userDetailsEntity: UserDetailsEntity) {
        userDatabase.UserDetailsDao().insertUserDetails(userDetailsEntity)
    }

    fun getAllUserDetails(): Flow<List<UserDetailsEntity>> = userDatabase.UserDetailsDao().getAllUserDetails()

    fun getUserDetailsById(userId: Int): Flow<UserDetailsEntity> =userDatabase.UserDetailsDao().getUserDetailsById(userId)


}