package com.vipuljha.jetnews.features.news.data.dto

import com.vipuljha.jetnews.features.news.domain.models.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)