package com.example.nicklefox.data_holder

import com.google.gson.annotations.SerializedName

data class Error(

    @SerializedName("code")
    val code: Int,

    @SerializedName("message")
    val message: String,

    @SerializedName("errors")
    val errors: List<ErrorDetails>,

    @SerializedName("status")
    val status: String,

)