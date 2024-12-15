package com.vipuljha.jetnews.features.news.domain.usecases

import com.vipuljha.jetnews.features.news.domain.repositories.NewsRepository

class GetNews(private val repository: NewsRepository) {
    operator fun invoke(sources: List<String>) = repository.getNews(sources)
}