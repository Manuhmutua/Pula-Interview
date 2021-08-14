package com.manuh.share.pulainterview.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "options_table")
data class Option(

    @ColumnInfo(name = "question_id")
    @Expose
    var question_id: String,

    @SerializedName("value")
    @ColumnInfo(name = "value")
    var value: String,

    @SerializedName("display_text")
    @ColumnInfo(name = "display_text")
    @PrimaryKey
    var display_text: String
) {
    constructor() : this("","", "")
}