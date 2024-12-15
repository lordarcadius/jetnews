package com.vipuljha.jetnews.features.news.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.vipuljha.jetnews.core.utils.Constants.NEWS_SOURCES
import com.vipuljha.jetnews.features.news.domain.usecases.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val newsUseCases: NewsUseCases) : ViewModel() {
    val news = newsUseCases.getNews(sources = NEWS_SOURCES).cachedIn(viewModelScope)
}