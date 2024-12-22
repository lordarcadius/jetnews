package com.vipuljha.jetnews.features.news.domain.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vipuljha.jetnews.core.utils.Constants.ARTICLES_TABLE
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
@Entity(tableName = ARTICLES_TABLE)
data class Article(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: Source,
    val title: String,
    val url: String,
    val urlToImage: String
): Parcelable