package com.vipuljha.jetnews.features.news.domain.repositories

import androidx.paging.PagingData
import com.vipuljha.jetnews.features.news.domain.models.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun getNews(sources: List<String>): Flow<PagingData<Article>>
    fun searchNews(searchQuery: String, sources: List<String>): Flow<PagingData<Article>>
    fun selectArticles(): Flow<List<Article>>
    suspend fun upsertArticle(article: Article)
    suspend fun deleteArticle(article: Article)
    suspend fun selectSingleArticle(url: String): Article?
}