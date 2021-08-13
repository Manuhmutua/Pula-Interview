package com.manuh.share.pulainterview.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "en_table")
data class En(
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    var id: Int,

    @SerializedName("q_farmer_name")
    @ColumnInfo(name = "q_farmer_name")
    var q_farmer_name: String,

    @SerializedName("q_farmer_gender")
    @ColumnInfo(name = "q_farmer_gender")
    var q_farmer_gender: String,

    @SerializedName("opt_male")
    @ColumnInfo(name = "opt_male")
    var opt_male: String,

    @SerializedName("opt_female")
    @ColumnInfo(name = "opt_female")
    var opt_female: String,

    @SerializedName("opt_other")
    @ColumnInfo(name = "opt_other")
    var opt_other: String,

    @SerializedName("q_size_of_farm")
    @ColumnInfo(name = "q_size_of_farm")
    var q_size_of_farm: String
){
    constructor() : this(0, "", "", "", "", "", "")
}