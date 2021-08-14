package com.manuh.share.pulainterview.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "response_table")
data class Response(
    @SerializedName("id")
    @ColumnInfo(name = "id")
    @PrimaryKey
    var id: String,

    @SerializedName("start_question_id")
    @ColumnInfo(name = "start_question_id")
    var start_question_id: String,

    @SerializedName("questions")
    @Ignore
    var questions: List<Question>?,

    @SerializedName("strings")
    @Ignore
    var strings: Strings?
) {
    constructor() : this("", "", null, null)
}