package com.vipuljha.jetnews.di

import com.vipuljha.jetnews.core.network.RetrofitProvider
import com.vipuljha.jetnews.features.news.data.datasources.remote.NewsApi
import com.vipuljha.jetnews.features.news.data.repositories.NewsRepositoryImpl
import com.vipuljha.jetnews.features.news.domain.repositories.NewsRepository
import com.vipuljha.jetnews.features.news.domain.usecases.GetNews
import com.vipuljha.jetnews.features.news.domain.usecases.NewsUseCases
import com.vipuljha.jetnews.features.news.domain.usecases.SearchNews
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
    fun provideNewsApi(): NewsApi {
        return RetrofitProvider.newsApi
    }

    @Provides
    @Singleton
    fun provideNewsRepository(newsApi: NewsApi): NewsRepository = NewsRepositoryImpl(newsApi)

    @Provides
    @Singleton
    fun provideNewsUseCases(repository: NewsRepository): NewsUseCases {
        return NewsUseCases(getNews = GetNews(repository), searchNews = SearchNews(repository))
    }

}