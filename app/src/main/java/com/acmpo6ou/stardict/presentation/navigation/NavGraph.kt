package com.acmpo6ou.stardict.presentation.navigation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.acmpo6ou.stardict.presentation.screens.ShortcutRouteScreen
import com.acmpo6ou.stardict.presentation.screens.dicts.DictsScreen
import com.acmpo6ou.stardict.presentation.screens.favorite.FavoritesScreen
import com.acmpo6ou.stardict.presentation.screens.word.WordScreen


@Composable
fun MyAppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = Destination.MainScreen,
    shortcutText: CharSequence?,
    enableAnimation: Boolean = true
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        homeScreen(navController)
        composable(Destination.DictsScreen) {
            DictsScreen(viewModel = hiltViewModel(), navController = navController)
        }
        composable(route = Destination.WordScreen) {
            WordScreen(p = it.arguments?.getParcelable("pArg")!!, navController = navController)
        }
        composable(Destination.ShortcutRouteScreen) {
            ShortcutRouteScreen(navController = navController, shortcutText = shortcutText)
        }
        composable(Destination.FavoritesScreen) {
            FavoritesScreen( navController = navController)
        }
        settingsScreen(navController = navController, enableAnimation = enableAnimation)
        aboutScreen(navController = navController, enableAnimation = enableAnimation)
    }
}



//fun NavController.navigate(
//    route: String,
//    args: Bundle,
//    navOptions: NavOptions? = null,
//    navigatorExtras: Navigator.Extras? = null
//) {
//    val nodeId = graph.findNode(route = route)?.id
//    if (nodeId != null) {
//        navigate(nodeId, args, navOptions, navigatorExtras)
//    }
//}



@SuppressLint("RestrictedApi")
fun NavController.navigate(
    route: String,
    args: Bundle,
    navOptions: NavOptions? = null,
    navigatorExtras: Navigator.Extras? = null
) {
    val routeLink = NavDeepLinkRequest
        .Builder
        .fromUri(NavDestination.createRoute(route).toUri())
        .build()

    val deepLinkMatch = graph.matchDeepLink(routeLink)
    if (deepLinkMatch != null) {
        val destination = deepLinkMatch.destination
        val id = destination.id
        navigate(id, args, navOptions, navigatorExtras)
    } else {
        navigate(route, navOptions, navigatorExtras)
    }
}
