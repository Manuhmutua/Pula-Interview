package com.manuh.share.pulainterview.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "answers_table")
data class Answer(
    @SerializedName("id")
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    var id: Int,

    @SerializedName("question_id")
    @ColumnInfo(name = "question_id")
    var question_id: String,

    @SerializedName("question_answer")
    @ColumnInfo(name = "question_answer")
    var question_answer: String
) {
    constructor() : this(0, "", "")
}