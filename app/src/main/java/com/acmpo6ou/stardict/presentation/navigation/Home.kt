package com.acmpo6ou.stardict.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.acmpo6ou.stardict.presentation.screens.main.MainScreen

enum class HomeNavigation(val route: String) {
    Home("Home"),
}

fun NavGraphBuilder.homeScreen(
    navController: NavController
) = navigation(
    startDestination = HomeNavigation.Home.route,
    route = Destination.MainScreen
) {
    composable(
        route = HomeNavigation.Home.route
    ) {
        MainScreen(
            navController = navController
        )
    }
}
