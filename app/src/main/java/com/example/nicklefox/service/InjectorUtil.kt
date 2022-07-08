package com.example.nicklefox.service

object InjectorUtil {
    fun provideReceptionRepository(): YouTubeRepository {
        val dataSource = YoutubeRepositoryImpl()
        return YouTubeRepository(dataSource)
    }
}