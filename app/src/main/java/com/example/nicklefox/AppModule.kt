package com.example.nicklefox

import com.example.nicklefox.service.YoutubeInterface
import com.example.nicklefox.service.YoutubeRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    /* @Singleton
     @Provides
     fun getRetrofit(): Retrofit {
         return Retrofit.Builder().baseUrl(Constants.API)
             .addConverterFactory(GsonConverterFactory.create()).build()
     }

     @Singleton
     @Provides
     fun getYoutubeResponse(retrofit: Retrofit): YoutubeApiResponse {
         return retrofit.create(YoutubeApiResponse::class.java)
     }*/

    @Provides
    @Singleton
    fun provideCYoutubeRepository(): YoutubeInterface = YoutubeRepositoryImpl()


    /* @Provides
     fun createService(): YoutubeRepository {
         val service = MainServiceCreator().createService(YoutubeService::class.java)
         val source = YoutubeDataSource(service)
         return YoutubeRepository(source)
     }*/
}