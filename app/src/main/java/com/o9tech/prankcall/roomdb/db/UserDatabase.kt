package com.o9tech.prankcall.roomdb.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.o9tech.prankcall.roomdb.dao.FakeMessageDao
import com.o9tech.prankcall.roomdb.dao.UserDao
import com.o9tech.prankcall.roomdb.dao.UserDetailsDao
import com.o9tech.prankcall.roomdb.dao.VideoDao
import com.o9tech.prankcall.roomdb.databaseModel.MessageEntity
import com.o9tech.prankcall.roomdb.databaseModel.UserDetailsEntity
import com.o9tech.prankcall.roomdb.databaseModel.UserEntity
import com.o9tech.prankcall.roomdb.databaseModel.VideoEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Database(entities = [UserEntity::class, MessageEntity::class, VideoEntity::class, UserDetailsEntity::class],version = 8, exportSchema = false)
abstract class UserDatabase : RoomDatabase(){

    abstract fun userDao(): UserDao
    abstract fun fakeMessageDao(): FakeMessageDao
    abstract fun videoDao(): VideoDao
    abstract fun UserDetailsDao(): UserDetailsDao



}





//@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
//abstract class UserDatabase : RoomDatabase() {
//    abstract fun userDao(): UserDao
//
//    companion object {
//        @Volatile
//        private var INSTANCE: UserDatabase? = null
//
//        fun getDatabase(context: Context): UserDatabase {
//            return INSTANCE ?: synchronized(this) {
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    UserDatabase::class.java,
//                    "user_database"
//                )
//                    .addCallback(roomCallback) // 👈 Callback add kiya hai
//                    .build()
//                INSTANCE = instance
//                instance
//            }
//        }
//
//        private val roomCallback = object : RoomDatabase.Callback() {
//            override fun onCreate(db: SupportSQLiteDatabase) {
//                super.onCreate(db)
//                CoroutineScope(Dispatchers.IO).launch {
//                    INSTANCE?.userDao()?.insertUser(
//                        listOf(
//                            UserEntity(name = "Ali", imageUri = "https://example.com/ali.jpg"),
//                            UserEntity(name = "Ahmed", imageUri = "https://example.com/ahmed.jpg"),
//                            UserEntity(name = "Zain", imageUri = "https://example.com/zain.jpg")
//                        )
//                    )
//                }
//            }
//        }
//    }
//}
