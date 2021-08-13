package com.manuh.share.pulainterview.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "questions_table")
data class Question(
    @SerializedName("id")
    @ColumnInfo(name = "id")
    @PrimaryKey
    var id: String,

    @SerializedName("question_type")
    @ColumnInfo(name = "question_type")
    var question_type: String,

    @SerializedName("answer_type")
    @ColumnInfo(name = "answer_type")
    var answer_type: String,

    @SerializedName("question_text")
    @ColumnInfo(name = "question_text")
    var question_text: String,

    @SerializedName("options")
    @Ignore
    var options: List<Option>?,

    @SerializedName("next")
    @ColumnInfo(name = "next")
    var next: String
){
    constructor() : this("", "", "", "", null, "")
}