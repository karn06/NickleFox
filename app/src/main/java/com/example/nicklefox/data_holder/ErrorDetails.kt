package com.example.nicklefox.data_holder

import com.google.gson.annotations.SerializedName

data class ErrorDetails(
    @SerializedName("message")
    val message: Int,

    @SerializedName("domain")
    val domain: Int,

    @SerializedName("reason")
    val reason: Int,
)