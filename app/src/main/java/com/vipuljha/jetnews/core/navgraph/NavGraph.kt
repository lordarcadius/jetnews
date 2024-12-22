package com.vipuljha.jetnews.core.navgraph

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vipuljha.jetnews.features.news.presentation.navigator.NewsNavigator
import com.vipuljha.jetnews.features.onboarding.presentation.screens.OnboardingScreen
import com.vipuljha.jetnews.features.onboarding.presentation.viewmodels.OnboardingViewModel

@Composable
fun NavGraph(startDestination: String, innerPadding: PaddingValues) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startDestination) {
        composable(OnboardingRoute.route) {
            val viewModel = hiltViewModel<OnboardingViewModel>()
            OnboardingScreen(event = viewModel::onEvent)
        }

        composable(NewsRoute.route) {
            NewsNavigator()
        }
    }
}