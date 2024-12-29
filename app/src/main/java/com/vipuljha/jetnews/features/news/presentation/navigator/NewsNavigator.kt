package com.vipuljha.jetnews.features.news.presentation.navigator

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.vipuljha.jetnews.R
import com.vipuljha.jetnews.core.navgraph.BookmarksRoute
import com.vipuljha.jetnews.core.navgraph.DetailsRoute
import com.vipuljha.jetnews.core.navgraph.HomeRoute
import com.vipuljha.jetnews.core.navgraph.SearchRoute
import com.vipuljha.jetnews.features.news.domain.models.Article
import com.vipuljha.jetnews.features.news.presentation.bookmark.state.BookmarkViewModel
import com.vipuljha.jetnews.features.news.presentation.bookmark.ui.BookmarkScreen
import com.vipuljha.jetnews.features.news.presentation.details.states.DetailsEvent
import com.vipuljha.jetnews.features.news.presentation.details.states.DetailsViewModel
import com.vipuljha.jetnews.features.news.presentation.details.ui.DetailsScreen
import com.vipuljha.jetnews.features.news.presentation.home.HomeScreen
import com.vipuljha.jetnews.features.news.presentation.home.HomeViewModel
import com.vipuljha.jetnews.features.news.presentation.navigator.components.NewsBottomNavigation
import com.vipuljha.jetnews.features.news.presentation.navigator.components.NewsBottomNavigationItem
import com.vipuljha.jetnews.features.news.presentation.search.states.SearchViewModel
import com.vipuljha.jetnews.features.news.presentation.search.ui.SearchScreen

@Composable
fun NewsNavigator() {
    val bottomNavigationItems = remember {
        listOf(
            NewsBottomNavigationItem(icon = R.drawable.ic_home, text = "Home"),
            NewsBottomNavigationItem(icon = R.drawable.ic_search, text = "Search"),
            NewsBottomNavigationItem(icon = R.drawable.ic_bookmark, text = "Bookmark"),
        )
    }

    val navController = rememberNavController()
    val backStackState = navController.currentBackStackEntryAsState().value
    var selectedItem by rememberSaveable {
        mutableIntStateOf(0)
    }

    selectedItem = when (backStackState?.destination?.route) {
        HomeRoute.route -> 0
        SearchRoute.route -> 1
        BookmarksRoute.route -> 2
        else -> 0
    }

    Scaffold(modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NewsBottomNavigation(
                items = bottomNavigationItems,
                selected = selectedItem,
                onItemClick = { index ->
                    when (index) {
                        0 -> navigateOnClick(navController, HomeRoute.route)
                        1 -> navigateOnClick(navController, SearchRoute.route)
                        2 -> navigateOnClick(navController, BookmarksRoute.route)
                    }
                }
            )
        }) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = HomeRoute.route,
            modifier = Modifier
                .fillMaxSize()
        ) {
            composable(HomeRoute.route) {
                val viewModel: HomeViewModel = hiltViewModel()
                val articles = viewModel.news.collectAsLazyPagingItems()
                HomeScreen(
                    innerPadding = innerPadding,
                    articles = articles,
                    navigateToSearch = { navigateOnClick(navController, SearchRoute.route) },
                    navigateToDetails = { article ->
                        navigateToDetailsScreen(navController, article)
                    },
                )
            }

            composable(SearchRoute.route) {
                val viewModel: SearchViewModel = hiltViewModel()
                val state = viewModel.state.value
                SearchScreen(
                    innerPadding = innerPadding,
                    state = state,
                    event = viewModel::onEvent,
                    navigateToDetails = {
                        navigateToDetailsScreen(
                            navController = navController,
                            article = it
                        )
                    },
                )
            }

            composable(DetailsRoute.route) {
                val viewModel: DetailsViewModel = hiltViewModel()
                val isBookmarked by viewModel.isBookmarked.collectAsState()

                if (viewModel.sideEffect != null) {
                    Toast.makeText(LocalContext.current, viewModel.sideEffect, Toast.LENGTH_SHORT)
                        .show()
                    viewModel.onEvent(DetailsEvent.RemoveSideEffect)
                }

                navController.previousBackStackEntry?.savedStateHandle?.get<Article>("article")
                    ?.let { article ->
                        viewModel.checkBookmarkState(article.url)
                        DetailsScreen(
                            article = article,
                            isBookmarked = isBookmarked,
                            event = viewModel::onEvent,
                            navigateUp = { navController.navigateUp() }
                        )
                    }
            }


            composable(BookmarksRoute.route) {
                val viewModel: BookmarkViewModel = hiltViewModel()
                val state = viewModel.state.value
                BookmarkScreen(
                    innerPadding = innerPadding,
                    state = state,
                    navigateToDetails = { article ->
                        navigateToDetailsScreen(navController = navController, article = article)
                    })
            }
        }
    }
}

fun navigateOnClick(navController: NavController, route: String) {
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let { homeScreen ->
            popUpTo(homeScreen) {
                saveState = true
            }
            restoreState = true
            launchSingleTop = true
        }
    }
}

private fun navigateToDetailsScreen(navController: NavController, article: Article) {
    navController.currentBackStackEntry?.savedStateHandle?.set("article", article)
    navController.navigate(route = DetailsRoute.route)
}