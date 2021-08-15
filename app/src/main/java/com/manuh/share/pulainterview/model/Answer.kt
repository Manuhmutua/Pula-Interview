package com.manuh.share.pulainterview.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "answers_table")
data class Answer(
    @SerializedName("id")
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = false)
    var id: String,

    @SerializedName("farmer_name")
    @ColumnInfo(name = "farmer_name")
    var farmer_name: String,

    @SerializedName("farmer_gender")
    @ColumnInfo(name = "farmer_gender")
    var farmer_gender: String,

    @SerializedName("farmer_land")
    @ColumnInfo(name = "farmer_land")
    var farmer_land: String
) {
    constructor() : this("", "", "", "")
}