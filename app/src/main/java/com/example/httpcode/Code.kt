package com.example.httpcode

import com.google.gson.annotations.SerializedName

//@SerializedName()
class Code(
    @SerializedName("path") val image_url: String,
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String)