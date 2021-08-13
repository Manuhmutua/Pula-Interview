package com.manuh.share.pulainterview.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.manuh.share.pulainterview.model.Option

@Dao
interface OptionsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOption(option: Option)

    @Query("SELECT * FROM options_table WHERE display_text=:displayText")
    fun getOptions(displayText: String): Option?
}