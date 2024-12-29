package com.vipuljha.jetnews.app

import android.graphics.Color.TRANSPARENT
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.vipuljha.jetnews.core.navgraph.NavGraph
import com.vipuljha.jetnews.core.theme.JetNewsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()
    private val lightTransparentStyle = SystemBarStyle.light(
        scrim = TRANSPARENT,
        darkScrim = TRANSPARENT
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                viewModel.splashCondition
            }
        }
        enableEdgeToEdge(
            navigationBarStyle = lightTransparentStyle
        )
        setContent {
            JetNewsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavGraph(
                        startDestination = viewModel.startDestination.route,
                        innerPadding = innerPadding
                    )
                }
            }
        }
    }
}