package com.example.nicklefox.data_holder

import com.google.gson.annotations.SerializedName

data class DataItem(

    @SerializedName("id")
    val id : ID,

    @SerializedName("snippet")
    val snippet : Snippet

)