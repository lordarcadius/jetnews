package com.vipuljha.jetnews.core.navgraph

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.vipuljha.jetnews.features.onboarding.presentation.screens.OnboardingScreen
import com.vipuljha.jetnews.features.onboarding.presentation.viewmodels.OnboardingViewModel

@Composable
fun NavGraph(startDestination: String, innerPadding: PaddingValues) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startDestination) {
        navigation(
            route = Routes.AppStartNavigation.route,
            startDestination = Routes.OnBoardingScreen.route
        ) {
            composable(route = Routes.OnBoardingScreen.route) {
                val viewModel = hiltViewModel<OnboardingViewModel>()
                OnboardingScreen(event = viewModel::onEvent)
            }
        }

        navigation(
            route = Routes.NewsNavigation.route,
            startDestination = Routes.NewsNavigatorScreen.route
        ) {
            composable(route = Routes.NewsNavigatorScreen.route) {
                Text(text = "News Navigator Screen", modifier = Modifier.padding(innerPadding))
            }
        }
    }
}