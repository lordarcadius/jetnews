package com.vipuljha.jetnews.features.news.domain.usecases

import com.vipuljha.jetnews.features.news.data.datasources.local.NewsDao
import com.vipuljha.jetnews.features.news.domain.models.Article

class DeleteArticle(private val newsDao: NewsDao) {
    suspend operator fun invoke(article: Article) = newsDao.delete(article)
}