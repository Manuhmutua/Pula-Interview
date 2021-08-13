package com.manuh.share.pulainterview.di

import android.app.Application
import androidx.room.Room.databaseBuilder
import com.manuh.share.pulainterview.dao.*
import com.manuh.share.pulainterview.source.database.Database
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDB(application: Application?): Database {
        return databaseBuilder(
            application!!.applicationContext,
            Database::class.java,
            "Questions_Database"
        )
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    @Singleton
    fun provideAnswerDao(database: Database): AnswerDao {
        return database.answerDao()!!
    }

    @Provides
    @Singleton
    fun provideEnDao(database: Database): EnDao {
        return database.enDao()!!
    }

    @Provides
    @Singleton
    fun provideOptionsDao(database: Database): OptionsDao {
        return database.optionsDao()!!
    }

    @Provides
    @Singleton
    fun provideQuestionDao(database: Database): QuestionDao {
        return database.questionDao()!!
    }

    @Provides
    @Singleton
    fun provideResponseDao(database: Database): ResponseDao {
        return database.responseDao()!!
    }
}