package com.manuh.share.pulainterview.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.manuh.share.pulainterview.model.En

@Dao
interface EnDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEn(en: En?)

    @Query("SELECT * FROM en_table WHERE id=0")
    fun getEn(): En?
}