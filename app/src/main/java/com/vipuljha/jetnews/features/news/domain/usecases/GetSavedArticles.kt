package com.vipuljha.jetnews.features.news.domain.usecases

import com.vipuljha.jetnews.features.news.domain.models.Article
import com.vipuljha.jetnews.features.news.domain.repositories.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetSavedArticles(private val newsRepository: NewsRepository) {
    operator fun invoke(): Flow<List<Article>> = newsRepository.selectArticles()
}