package com.example.nicklefox.service

import com.example.nicklefox.data_holder.YoutubeApiResponse
import javax.inject.Inject

class YouTubeRepository @Inject constructor(
    private val dataSource: YoutubeInterface
) {
    suspend fun getList(
        maxSize: Int,
    ): DataWrapper<YoutubeApiResponse> {
        return dataSource.getYouTubeData(maxSize)
    }
}