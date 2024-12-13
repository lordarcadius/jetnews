package com.vipuljha.jetnews.app

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vipuljha.jetnews.core.navgraph.NewsRoute
import com.vipuljha.jetnews.core.navgraph.OnboardingRoute
import com.vipuljha.jetnews.core.navgraph.Route
import com.vipuljha.jetnews.features.onboarding.domain.usecases.AppEntryUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(appEntryUseCases: AppEntryUseCases) : ViewModel() {
    var splashCondition by mutableStateOf(true)
        private set

    var startDestination: Route by mutableStateOf(OnboardingRoute)
        private set

    init {
        appEntryUseCases.readAppEntry().onEach { shouldStartFromHomeScreen ->
            startDestination = if (shouldStartFromHomeScreen) {
                NewsRoute
            } else {
                OnboardingRoute
            }
            delay(300)
            splashCondition = false
        }.launchIn(viewModelScope)
    }
}