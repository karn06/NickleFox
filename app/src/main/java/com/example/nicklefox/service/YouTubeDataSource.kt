package com.example.nicklefox.service

import com.example.nicklefox.Constants

class YoutubeDataSource constructor(private val service: YoutubeService) : BaseDataSource() {

    suspend fun getYouTubeData(maxResult: Int) =
        execute { service.getYouTubeData("snippet", maxResult, Constants.API_KEY) }
}