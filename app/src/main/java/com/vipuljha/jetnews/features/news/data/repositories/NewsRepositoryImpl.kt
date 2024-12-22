package com.vipuljha.jetnews.features.news.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.vipuljha.jetnews.features.news.data.datasources.local.NewsDao
import com.vipuljha.jetnews.features.news.data.datasources.remote.NewsApi
import com.vipuljha.jetnews.features.news.data.datasources.remote.NewsPagingSource
import com.vipuljha.jetnews.features.news.data.datasources.remote.SearchNewsPagingSource
import com.vipuljha.jetnews.features.news.domain.models.Article
import com.vipuljha.jetnews.features.news.domain.repositories.NewsRepository
import kotlinx.coroutines.flow.Flow

class NewsRepositoryImpl(
    private val newsApi: NewsApi,
    private val newsDao: NewsDao
) : NewsRepository {
    override fun getNews(sources: List<String>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                NewsPagingSource(
                    newsApi = newsApi,
                    sources = sources.joinToString(separator = ",")
                )
            }
        ).flow
    }

    override fun searchNews(searchQuery: String, sources: List<String>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                SearchNewsPagingSource(
                    newsApi = newsApi,
                    searchQuery = searchQuery,
                    sources = sources.joinToString(separator = ",")
                )
            }
        ).flow
    }

    override suspend fun upsertArticle(article: Article) {
        newsDao.upsert(article)
    }

    override suspend fun deleteArticle(article: Article) {
        newsDao.delete(article)
    }

    override fun selectArticles(): Flow<List<Article>> {
        return newsDao.select()
    }

    override suspend fun selectSingleArticle(url: String): Article? {
        return newsDao.getArticle(url)
    }
}