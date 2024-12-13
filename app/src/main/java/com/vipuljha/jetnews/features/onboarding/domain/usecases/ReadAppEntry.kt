package com.vipuljha.jetnews.features.onboarding.domain.usecases

import com.vipuljha.jetnews.features.onboarding.domain.OnboardingManager
import kotlinx.coroutines.flow.Flow

class ReadAppEntry(private val onboardingManager: OnboardingManager) {
    operator fun invoke(): Flow<Boolean> {
        return onboardingManager.readAppEntry()
    }
}