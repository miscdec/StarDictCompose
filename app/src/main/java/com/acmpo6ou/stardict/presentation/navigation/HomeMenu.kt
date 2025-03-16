package com.acmpo6ou.stardict.presentation.navigation

import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.ui.unit.sp

enum class HomeMenu(
    val title:String,
    val route:String,
) {
    Dictionaries(title = "Dictionaries",route = Destination.DictsScreen),
    Favorites(title = "Favorites",route = Destination.FavoritesScreen),
    Settings(title = "Settings",route = Destination.SettingsScreen),
    About(title = "About",route = Destination.AboutScreen)
}

val menus = HomeMenu.entries.toTypedArray()