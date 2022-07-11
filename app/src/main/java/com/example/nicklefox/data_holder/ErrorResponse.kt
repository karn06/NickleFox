package com.example.nicklefox.data_holder

import com.google.gson.annotations.SerializedName

data class ErrorResponse(

    @SerializedName("error")
    val error: Error,

    )