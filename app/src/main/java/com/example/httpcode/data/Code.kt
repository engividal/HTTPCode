package com.example.httpcode.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "code_table")
data class Code(

    @PrimaryKey @ColumnInfo(name = "code")
    @SerializedName("code")
    val title: String,

    @SerializedName("path") val image_url: String,
    @SerializedName("description") val description: String
)