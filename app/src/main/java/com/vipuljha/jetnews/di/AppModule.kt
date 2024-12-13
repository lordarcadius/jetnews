package com.vipuljha.jetnews.di

import android.app.Application
import com.vipuljha.jetnews.features.onboarding.data.OnboardingManagerImpl
import com.vipuljha.jetnews.features.onboarding.domain.OnboardingManager
import com.vipuljha.jetnews.features.onboarding.domain.usecases.AppEntryUseCases
import com.vipuljha.jetnews.features.onboarding.domain.usecases.ReadAppEntry
import com.vipuljha.jetnews.features.onboarding.domain.usecases.SaveAppEntry
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Provides
    @Singleton
    fun provideOnboardingManager(application: Application): OnboardingManager {
        return OnboardingManagerImpl(application)
    }

    @Provides
    @Singleton
    fun provideAppEntryUseCases(onboardingManager: OnboardingManager): AppEntryUseCases {
        return AppEntryUseCases(
            saveAppEntry = SaveAppEntry(onboardingManager),
            readAppEntry = ReadAppEntry(onboardingManager)
        )
    }
}