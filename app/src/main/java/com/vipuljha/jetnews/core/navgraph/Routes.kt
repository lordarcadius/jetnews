package com.vipuljha.jetnews.core.navgraph

sealed class Routes(val route: String) {
    object OnBoardingScreen : Routes("onBoardingScreen")
    object HomeScreen : Routes("homeScreen")
    object SearchScreen : Routes("searchScreen")
    object BookmarksScreen : Routes("bookmarksScreen")
    object DetailsScreen : Routes("detailsScreen")
    object AppStartNavigation : Routes("appStartNavigation")
    object NewsNavigation : Routes("newsNavigation")
    object NewsNavigatorScreen : Routes("newsNavigator")
}