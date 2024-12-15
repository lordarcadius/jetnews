package com.vipuljha.jetnews.core.utils

import com.vipuljha.jetnews.BuildConfig

object Constants {
    const val BASE_URL = "https://newsapi.org/"
    const val USER_SETTINGS = "userSettings"
    const val APP_ENTRY_KEY = "appEntry"
    const val API_KEY = BuildConfig.apiKey //Store in local.properties as apiKey variable
    val NEWS_SOURCES = listOf(
        "google-news-in",
        "abc-news",
        "bbc-news",
        "business-insider",
        "engadget",
        "espn",
        "ign",
        "techcrunch",
        "the-wall-street-journal"
    )
}