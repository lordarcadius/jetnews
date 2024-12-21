package com.vipuljha.jetnews.features.news.domain.usecases

data class NewsUseCases(
    val getNews: GetNews,
    val searchNews: SearchNews,
    val upsertArticle: UpsertArticle,
    val deleteArticle: DeleteArticle,
    val getSavedArticles: GetSavedArticles
)