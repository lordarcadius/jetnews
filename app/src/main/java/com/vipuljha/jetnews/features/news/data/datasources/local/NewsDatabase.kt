package com.vipuljha.jetnews.features.news.data.datasources.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.vipuljha.jetnews.features.news.domain.models.Article

@Database(entities = [Article::class], version = 1)
@TypeConverters(NewsTypeConverter::class)
abstract class NewsDatabase : RoomDatabase(){
    abstract val newsDao: NewsDao
}