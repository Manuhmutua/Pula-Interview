package com.manuh.share.pulainterview.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.manuh.share.pulainterview.model.Answer

@Dao
interface AnswerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAnswer(answer: Answer)

    @Query("SELECT * FROM answers_table")
    fun getAnswers(): LiveData<List<Answer?>?>?
}