package com.example.nicklefox.data_holder

import com.google.gson.annotations.SerializedName

data class PageInfo(

    @SerializedName("totalResults")
    val totalResults: Int,

    @SerializedName("resultsPerPage")
    val resultsPerPage: Int,

    )