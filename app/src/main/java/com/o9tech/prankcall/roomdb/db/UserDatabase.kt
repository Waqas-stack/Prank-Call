package com.o9tech.prankcall.roomdb.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.o9tech.prankcall.roomdb.dao.AudioDao
import com.o9tech.prankcall.roomdb.dao.FakeMessageDao
import com.o9tech.prankcall.roomdb.dao.UserDao
import com.o9tech.prankcall.roomdb.dao.UserDetailsDao
import com.o9tech.prankcall.roomdb.dao.VideoDao
import com.o9tech.prankcall.roomdb.databaseModel.AudioCallEntity
import com.o9tech.prankcall.roomdb.databaseModel.MessageEntity
import com.o9tech.prankcall.roomdb.databaseModel.UserDetailsEntity
import com.o9tech.prankcall.roomdb.databaseModel.UserEntity
import com.o9tech.prankcall.roomdb.databaseModel.VideoEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Database(entities = [UserEntity::class, MessageEntity::class, VideoEntity::class, UserDetailsEntity::class, AudioCallEntity::class],version = 9, exportSchema = false)
abstract class UserDatabase : RoomDatabase(){
    abstract fun userDao(): UserDao
    abstract fun fakeMessageDao(): FakeMessageDao
    abstract fun videoDao(): VideoDao
    abstract fun UserDetailsDao(): UserDetailsDao
    abstract fun audioDao(): AudioDao

}
