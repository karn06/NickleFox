package com.example.nicklefox.data_holder

import com.google.gson.annotations.SerializedName

data class Snippet(
    @SerializedName("title")
    val title: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("thumbnails")
    val thumbnails: ThumbNails,

    @SerializedName("channelTitle")
    val channelTitle: String,

    @SerializedName("publishedAt")
    val publishedAt: String,
)
