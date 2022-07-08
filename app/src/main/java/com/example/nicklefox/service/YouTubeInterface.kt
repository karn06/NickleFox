package com.example.nicklefox.service

import com.example.nicklefox.data_holder.YoutubeApiResponse

interface YoutubeInterface {

    suspend fun getYouTubeData(
        maxSize: Int
    ): DataWrapper<YoutubeApiResponse>
}