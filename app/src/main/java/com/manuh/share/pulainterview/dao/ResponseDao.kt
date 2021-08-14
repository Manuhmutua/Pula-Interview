package com.manuh.share.pulainterview.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.manuh.share.pulainterview.model.Response

@Dao
interface ResponseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertResponse(response: Response)

    @Query("SELECT * FROM response_table")
    fun getResponse(): LiveData<Response?>?
}