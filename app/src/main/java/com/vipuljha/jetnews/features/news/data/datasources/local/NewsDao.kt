package com.vipuljha.jetnews.features.news.data.datasources.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vipuljha.jetnews.features.news.domain.models.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {

    //Update or Insert an article in the database
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(article: Article): Long

    //Delete an Article from the Database
    @Delete
    suspend fun delete(article: Article)

    //Get the Flow of all the saved articles
    @Query("SELECT * FROM articles")
    fun select(): Flow<List<Article>>

    //Get one article based on the URL
    @Query("SELECT * FROM articles WHERE url=:url")
    suspend fun getArticle(url: String): Article?
}