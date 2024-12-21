package com.vipuljha.jetnews.di

import android.app.Application
import androidx.room.Room
import com.vipuljha.jetnews.core.network.RetrofitProvider
import com.vipuljha.jetnews.core.utils.Constants.DATABASE_NAME
import com.vipuljha.jetnews.features.news.data.datasources.local.NewsDao
import com.vipuljha.jetnews.features.news.data.datasources.local.NewsDatabase
import com.vipuljha.jetnews.features.news.data.datasources.local.NewsTypeConverter
import com.vipuljha.jetnews.features.news.data.datasources.remote.NewsApi
import com.vipuljha.jetnews.features.news.data.repositories.NewsRepositoryImpl
import com.vipuljha.jetnews.features.news.domain.repositories.NewsRepository
import com.vipuljha.jetnews.features.news.domain.usecases.DeleteArticle
import com.vipuljha.jetnews.features.news.domain.usecases.GetNews
import com.vipuljha.jetnews.features.news.domain.usecases.GetSavedArticles
import com.vipuljha.jetnews.features.news.domain.usecases.NewsUseCases
import com.vipuljha.jetnews.features.news.domain.usecases.SearchNews
import com.vipuljha.jetnews.features.news.domain.usecases.UpsertArticle
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
    fun provideNewsUseCases(repository: NewsRepository, newsDao: NewsDao): NewsUseCases {
        return NewsUseCases(
            getNews = GetNews(repository),
            searchNews = SearchNews(repository),
            upsertArticle = UpsertArticle(newsDao),
            deleteArticle = DeleteArticle(newsDao),
            getSavedArticles = GetSavedArticles(newsDao)
        )
    }

    @Provides
    @Singleton
    fun provideNewsDatabase(application: Application): NewsDatabase {
        return Room.databaseBuilder(
            context = application,
            klass = NewsDatabase::class.java,
            name = DATABASE_NAME,
        ).addTypeConverter(NewsTypeConverter())
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsDao(newsDatabase: NewsDatabase): NewsDao = newsDatabase.newsDao

}