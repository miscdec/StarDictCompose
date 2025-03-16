package com.acmpo6ou.stardict.presentation.navigation

import android.view.animation.AlphaAnimation
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.acmpo6ou.stardict.presentation.screens.preference.SettingsScreen
import com.sanmer.mrepo.ui.animate.slideInRightToLeft
import com.sanmer.mrepo.ui.animate.slideOutLeftToRight
import com.acmpo6ou.stardict.presentation.screens.settings.app.AppScreen


enum class SettingsNavigation(val route: String) {
    Home("Settings"),
    Style("style"),
    Repositories("Repositories"),
    App("App"),
    WorkingMode("WorkingMode"),

}

private val subScreens = listOf(
    SettingsNavigation.Style.route,
)
fun NavGraphBuilder.settingsScreen(
    navController: NavController,
    enableAnimation: Boolean = true,
) = navigation(
    startDestination = SettingsNavigation.Home.route,
    route = Destination.SettingsScreen
) {
    composable(
        route = SettingsNavigation.Home.route,
        enterTransition = {
            if (enableAnimation){
                if (initialState.destination.route in subScreens) {
                    slideInRightToLeft() + fadeIn()
                } else {
                    fadeIn()
                }
            }else null

        },
        exitTransition = {
            if (enableAnimation){
                if (targetState.destination.route in subScreens) {
                    slideOutLeftToRight() +  fadeOut()
                } else {
                    fadeOut()
                }
            }else null
        },
    ) {
        SettingsScreen(
            navController = navController
        )
    }

    composable(
        route = SettingsNavigation.Style.route,
        enterTransition = {
            if (enableAnimation){
                if (initialState.destination.route in subScreens) {
                    slideInRightToLeft() + fadeIn()
                } else {
                    fadeIn()
                }
            }else null

        },
        exitTransition = {
            if (enableAnimation){
                if (targetState.destination.route in subScreens) {
                    slideOutLeftToRight() +  fadeOut()
                } else {
                    fadeOut()
                }
            }else null
        },
    ) {
        AppScreen(navController = navController)
    }

}