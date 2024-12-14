package com.vipuljha.jetnews.di

import com.vipuljha.jetnews.core.network.RetrofitProvider
import com.vipuljha.jetnews.features.news.data.datasources.remote.NewsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun providerNewsApi(): NewsApi {
        return RetrofitProvider.newsApi
    }

}