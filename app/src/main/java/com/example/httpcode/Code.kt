package com.example.httpcode

import com.google.gson.annotations.SerializedName

data class Code(
    @SerializedName("path") val image_url: String,
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String)