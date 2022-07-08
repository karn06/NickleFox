package com.example.nicklefox.data_holder

import com.google.gson.annotations.SerializedName

data class DefaultImage(

    @SerializedName("url")
    val url : String,

    @SerializedName("width")
    val width : Int,

    @SerializedName("height")
    val height : Int,
)