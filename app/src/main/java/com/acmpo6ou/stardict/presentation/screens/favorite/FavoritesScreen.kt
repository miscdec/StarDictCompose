/*
 * StarDict implementation for Android.
 * Copyright (C) 2022  Bohdan Kolvakh
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *
 */

package com.acmpo6ou.stardict.presentation.screens.favorite

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.acmpo6ou.stardict.presentation.ui.component.common.AppBar
import com.acmpo6ou.stardict.presentation.ui.theme.StarDictTheme

@Composable
fun FavoritesScreen(
    viewModel: FavoritesViewModel = hiltViewModel(),
    navController: NavController
) {
    val favorites by viewModel.favorites.collectAsStateWithLifecycle(listOf())

    Scaffold(
        topBar = {
            AppBar( "Favorites", navController = navController)
        }
    ) {
        LazyColumn(modifier = Modifier.padding(it)) {
            items(favorites){item ->
                Row(
                    modifier = Modifier
                        .clickable {
                            viewModel.removeFavorite(item)
                        }
                        .padding(16.dp)
                        .fillMaxWidth()
                ) {
                    Text(item.word, fontSize = 26.sp)
                }
            }
        }
    }
}

//@Preview
//@Composable
//fun FavoritesScreenPreview() {
//    val model = FavoritesViewModel()
//    model.favorites.value = listOf("apple", "communication", "testing")
//
//    StarDictTheme {
//        FavoritesScreen(navController = rememberNavController())
//    }
//}
