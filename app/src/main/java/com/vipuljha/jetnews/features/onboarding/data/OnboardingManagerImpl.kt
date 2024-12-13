package com.vipuljha.jetnews.features.onboarding.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.vipuljha.jetnews.core.utils.Constants.APP_ENTRY_KEY
import com.vipuljha.jetnews.core.utils.Constants.USER_SETTINGS
import com.vipuljha.jetnews.features.onboarding.domain.OnboardingManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class OnboardingManagerImpl(private val context: Context) : OnboardingManager {
    override suspend fun saveAppEntry() {
        context.dataStore.edit { settings ->
            settings[PreferencesKey.APP_ENTRY] = true
        }
    }

    override fun readAppEntry(): Flow<Boolean> {
        return context.dataStore.data.map { preferences ->
            preferences[PreferencesKey.APP_ENTRY] ?: false
        }
    }
}

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = USER_SETTINGS)

private object PreferencesKey {
    val APP_ENTRY = booleanPreferencesKey(name = APP_ENTRY_KEY)
}