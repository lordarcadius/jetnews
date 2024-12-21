package com.vipuljha.jetnews.features.news.domain.usecases

import com.vipuljha.jetnews.features.news.domain.repositories.NewsRepository

class SearchNews(private val repository: NewsRepository) {
    operator fun invoke(searchQuery: String, sources: List<String>) =
        repository.searchNews(searchQuery = searchQuery, sources = sources)
}