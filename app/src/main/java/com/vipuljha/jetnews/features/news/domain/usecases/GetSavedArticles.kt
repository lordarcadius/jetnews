package com.vipuljha.jetnews.features.news.domain.usecases

import com.vipuljha.jetnews.features.news.data.datasources.local.NewsDao
import com.vipuljha.jetnews.features.news.domain.models.Article
import kotlinx.coroutines.flow.Flow

class GetSavedArticles(private val newsDao: NewsDao) {
    operator fun invoke(): Flow<List<Article>> = newsDao.select()
}