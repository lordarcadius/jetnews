package com.vipuljha.jetnews.core.utils

import com.vipuljha.jetnews.BuildConfig

object Constants {
    const val BASE_URL = "https://newsapi.org/"
    const val USER_SETTINGS = "userSettings"
    const val APP_ENTRY_KEY = "appEntry"
    //This API key shall be stored into local.properties as apiKey variable
    const val API_KEY = BuildConfig.apiKey
}