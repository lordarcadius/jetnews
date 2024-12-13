package com.vipuljha.jetnews.features.onboarding.domain

import kotlinx.coroutines.flow.Flow

interface OnboardingManager {
    suspend fun saveAppEntry()
    fun readAppEntry(): Flow<Boolean>
}