package com.vipuljha.jetnews.features.news.presentation.bookmark.state

import com.vipuljha.jetnews.features.news.domain.models.Article

data class BookmarkState (
    val articles: List<Article> = emptyList(),
)