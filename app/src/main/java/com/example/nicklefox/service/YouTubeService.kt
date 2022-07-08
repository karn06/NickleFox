package com.example.nicklefox.service

import com.example.nicklefox.Constants
import com.example.nicklefox.data_holder.YoutubeApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface YoutubeService {

    @GET(Constants.API_END_POINT)
    suspend fun getYouTubeData(
        @Query("part") part: String,
        @Query("maxResults") maxResults: Int,
        @Query("key") key: String,
    ): Response<YoutubeApiResponse>

}