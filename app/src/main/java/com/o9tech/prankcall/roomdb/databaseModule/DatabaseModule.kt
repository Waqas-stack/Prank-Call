package com.o9tech.prankcall.roomdb.databaseModule

import android.content.Context
import androidx.room.Room
import com.o9tech.prankcall.roomdb.dao.AudioDao
import com.o9tech.prankcall.roomdb.dao.FakeMessageDao
import com.o9tech.prankcall.roomdb.dao.UserDao
import com.o9tech.prankcall.roomdb.dao.UserDetailsDao
import com.o9tech.prankcall.roomdb.dao.VideoDao
import com.o9tech.prankcall.roomdb.databaseModel.AudioCallEntity
import com.o9tech.prankcall.roomdb.db.UserDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): UserDatabase {
        return Room.databaseBuilder(
            context,
            UserDatabase::class.java,
            "user_database"
        ).fallbackToDestructiveMigration()
            .allowMainThreadQueries().build()
    }


    @Provides
    fun provideUserDao(db: UserDatabase): UserDao = db.userDao()

    @Provides
    fun provideFakeMessageDao(db: UserDatabase): FakeMessageDao = db.fakeMessageDao()

    @Provides
    fun provideVideoDao(db: UserDatabase): VideoDao = db.videoDao()

    @Provides
    fun provideUserDetails(db: UserDatabase): UserDetailsDao = db.UserDetailsDao()

    @Provides
    fun provideAudioDao(db: UserDatabase): AudioDao = db.audioDao()

}
