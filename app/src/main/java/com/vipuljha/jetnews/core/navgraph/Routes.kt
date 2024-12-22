package com.vipuljha.jetnews.core.navgraph

import kotlinx.serialization.Serializable

sealed interface Route {
    val route: String
}

@Serializable
data object OnboardingRoute : Route {
    override val route = "onBoardingScreen"
}

@Serializable
data object NewsRoute : Route {
    override val route = "newsScreen"
}

@Serializable
data object HomeRoute : Route {
    override val route = "homeScreen"
}

@Serializable
data object SearchRoute : Route {
    override val route = "searchScreen"
}

@Serializable
data object DetailsRoute : Route {
    override val route = "detailsScreen"
}

@Serializable
data object BookmarksRoute : Route {
    override val route = "bookmarksScreen"
}
