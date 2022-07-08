package com.example.nicklefox.service

import com.example.nicklefox.data_holder.YoutubeApiResponse

class YoutubeRepositoryImpl : YoutubeInterface {

    override suspend fun getYouTubeData(
        maxSize: Int
    ): DataWrapper<YoutubeApiResponse> {

        val response = createService().getYouTubeData(maxSize)
        return when (response.status) {
            DataWrapper.Status.SUCCESS -> {
                val deliverResult = DataWrapper(
                    DataWrapper.Status.SUCCESS,
                    response.data,
                    response.message,
                    response.statusCode
                )
                deliverResult
            }
            DataWrapper.Status.ERROR -> {

                val deliverResult =
                    DataWrapper(
                        DataWrapper.Status.ERROR,
                        null,
                        response.message,
                        response.statusCode
                    )

                deliverResult

            }
        }
    }

    fun createService(): YoutubeDataSource {
        val service = MainServiceCreator().createService(YoutubeService::class.java)
        val source = YoutubeDataSource(service)
        return YoutubeDataSource(service)
    }
}