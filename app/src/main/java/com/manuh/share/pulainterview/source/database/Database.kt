package com.manuh.share.pulainterview.source.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.manuh.share.pulainterview.dao.*
import com.manuh.share.pulainterview.model.*

@Database(
    entities = [Answer::class, En::class, Option::class, Question::class, Response::class],
    version = 2,
    exportSchema = false
)
abstract class Database : RoomDatabase() {
    abstract fun answerDao(): AnswerDao?

    abstract fun enDao(): EnDao?

    abstract fun optionsDao(): OptionsDao?

    abstract fun questionDao(): QuestionDao?

    abstract fun responseDao(): ResponseDao?
}
