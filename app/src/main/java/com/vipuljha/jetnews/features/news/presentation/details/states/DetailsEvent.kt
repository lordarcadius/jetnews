package com.vipuljha.jetnews.features.news.presentation.details.states

import com.vipuljha.jetnews.features.news.domain.models.Article

sealed class DetailsEvent {
    data class UpsertDeleteArticle(val article: Article) : DetailsEvent()
    data object RemoveSideEffect : DetailsEvent()
}