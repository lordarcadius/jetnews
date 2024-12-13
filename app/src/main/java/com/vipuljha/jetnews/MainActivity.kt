package com.vipuljha.jetnews

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
import com.vipuljha.jetnews.core.theme.JetNewsTheme
import com.vipuljha.jetnews.features.onboarding.domain.usecases.AppEntryUseCases
import com.vipuljha.jetnews.features.onboarding.presentation.screens.OnboardingScreen
import com.vipuljha.jetnews.features.onboarding.presentation.viewmodels.OnboardingViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var useCases: AppEntryUseCases

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge()
        lifecycleScope.launch {
            useCases.readAppEntry().collect {
                println(it)
            }
        }
        setContent {
            JetNewsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val viewModel = hiltViewModel<OnboardingViewModel>()
                    OnboardingScreen(
                        padding = innerPadding,
                        event = viewModel::onEvent
                    )
                }
            }
        }
    }
}