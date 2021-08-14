package com.manuh.share.pulainterview.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.manuh.share.pulainterview.model.Option

@Dao
interface OptionsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOption(option: Option)

    @Query("SELECT * FROM options_table WHERE question_id=:question_id")
    fun getOptions(question_id: String): List<Option?>?
}