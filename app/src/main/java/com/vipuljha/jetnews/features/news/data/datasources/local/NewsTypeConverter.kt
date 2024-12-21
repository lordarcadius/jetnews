package com.vipuljha.jetnews.features.news.data.datasources.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.vipuljha.jetnews.features.news.domain.models.Source

@ProvidedTypeConverter
class NewsTypeConverter {

    @TypeConverter
    fun fromSource(source: Source): String {
        return "${source.id}, ${source.name}"
    }

    @TypeConverter
    fun toSource(source: String): Source {
        return source.split(",").let { sourceArray ->
            Source(id = sourceArray[0], name = sourceArray[1])
        }
    }
}