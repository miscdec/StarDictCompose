package com.acmpo6ou.stardict.presentation.navigation

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation

import com.acmpo6ou.stardict.presentation.screens.about.AboutScreen
import com.sanmer.mrepo.ui.animate.slideInRightToLeft
import com.sanmer.mrepo.ui.animate.slideOutLeftToRight

enum class AboutNavigation(val route: String) {
    About("About")
}

fun NavGraphBuilder.aboutScreen(
    navController: NavController,
    enableAnimation: Boolean = true
) = navigation(
    startDestination = AboutNavigation.About.route,
    route = Destination.AboutScreen
) {
    composable(
        route = AboutNavigation.About.route,
        enterTransition = {
            if (enableAnimation){
                slideInRightToLeft() + fadeIn()
            }else null

        },
        exitTransition = {
            if (enableAnimation){
                slideOutLeftToRight() +  fadeOut()
            }else null
        },
    ) {
        AboutScreen(
            navController = navController
        )
    }
}
