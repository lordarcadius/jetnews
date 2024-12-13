package com.vipuljha.jetnews.features.onboarding.domain.usecases

import com.vipuljha.jetnews.features.onboarding.domain.OnboardingManager

class SaveAppEntry(private val onboardingManager: OnboardingManager) {
    suspend operator fun invoke(){
        onboardingManager.saveAppEntry()
    }
}