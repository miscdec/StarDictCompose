package com.acmpo6ou.stardict.presentation.screens.main

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Translate
import androidx.compose.ui.graphics.vector.ImageVector

enum class HomeTabsItems(
    val title: String,
    val icon: ImageVector,
) {
    Dictionary("查词", Icons.Filled.Search),
    Translation("翻译", Icons.Filled.Translate),
}