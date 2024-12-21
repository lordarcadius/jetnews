package com.vipuljha.jetnews.features.news.presentation.details.states

sealed class DetailsEvent {
    data object SaveArticle : DetailsEvent()
}