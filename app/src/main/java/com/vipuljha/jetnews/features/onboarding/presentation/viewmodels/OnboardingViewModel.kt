package com.vipuljha.jetnews.features.onboarding.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vipuljha.jetnews.features.onboarding.domain.usecases.AppEntryUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val appEntryUseCases: AppEntryUseCases
) : ViewModel() {

    fun onEvent(events: OnboardingEvents) {
        when (events) {
            is OnboardingEvents.SaveEntry -> saveEntry()
        }
    }

    private fun saveEntry() {
        viewModelScope.launch {
            appEntryUseCases.saveAppEntry()
        }
    }
}