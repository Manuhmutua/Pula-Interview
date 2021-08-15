package com.manuh.share.pulainterview.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.manuh.share.pulainterview.model.Answer

@Dao
interface AnswerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAnswer(answer: Answer)

    @Query("SELECT * FROM answers_table")
    fun getAnswers(): LiveData<List<Answer?>?>?

    @Update
    fun updateAnswer(answer: Answer?)

    @Query("UPDATE answers_table SET farmer_land = :amount WHERE id =:id")
    fun updateLand(amount: String, id: String)
}