package com.manuh.share.pulainterview.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.manuh.share.pulainterview.model.Question

@Dao
interface QuestionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertQuestion(question: Question)

    @Query("SELECT * FROM questions_table WHERE id=:id")
    fun getQuestion(id: String): Question?
}