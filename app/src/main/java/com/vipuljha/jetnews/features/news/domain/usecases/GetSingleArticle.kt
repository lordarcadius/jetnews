package com.vipuljha.jetnews.features.news.domain.usecases

import com.vipuljha.jetnews.features.news.domain.models.Article
import com.vipuljha.jetnews.features.news.domain.repositories.NewsRepository

class GetSingleArticle(private val newsRepository: NewsRepository) {
    suspend operator fun invoke(url: String): Article? = newsRepository.selectSingleArticle(url)
}