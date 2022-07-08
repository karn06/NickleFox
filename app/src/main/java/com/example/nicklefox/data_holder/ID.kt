package com.example.nicklefox.data_holder

import com.google.gson.annotations.SerializedName

data class ID(

    @SerializedName("kind")
    val kind: String,

    @SerializedName("videoId")
    val videoId: String
)