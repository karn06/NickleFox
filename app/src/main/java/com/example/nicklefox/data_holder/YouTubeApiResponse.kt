package com.example.nicklefox.data_holder

import com.google.gson.annotations.SerializedName

data class YoutubeApiResponse(
    @SerializedName("items")
    val listOfData: List<DataItem>,

    @SerializedName("pageInfo")
    val pageInfo: PageInfo
)
