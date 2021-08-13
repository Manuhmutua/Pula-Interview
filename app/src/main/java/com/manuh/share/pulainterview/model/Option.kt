package com.manuh.share.pulainterview.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "options_table")
data class Option(

    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    var id: Int,

    @SerializedName("value")
    @ColumnInfo(name = "value")
    var value: String,

    @SerializedName("display_text")
    @ColumnInfo(name = "display_text")
    var display_text: String
) {
    constructor() : this(0,"", "")
}