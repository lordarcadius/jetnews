package com.vipuljha.jetnews.features.news.presentation.search.states

sealed class SearchEvent {
    data class UpdateSearchQuery(val searchQuery: String) : SearchEvent()
    data object SearchNews : SearchEvent()
}