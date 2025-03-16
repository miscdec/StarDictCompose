package com.acmpo6ou.stardict.presentation.screens

import androidx.compose.runtime.Composable
import androidx.core.os.bundleOf
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.navOptions
import org.yage.dict.star.WordParams
import com.acmpo6ou.stardict.presentation.navigation.Destination
import com.acmpo6ou.stardict.presentation.navigation.navigate
import com.acmpo6ou.stardict.presentation.screens.main.MainViewModel

@Composable
fun ShortcutRouteScreen(navController: NavController, shortcutText: CharSequence?){


    val mainViewModel: MainViewModel = hiltViewModel()

    if (!shortcutText.isNullOrEmpty()){
        val text = shortcutText.toString()
            .lowercase()
            .trim()
        mainViewModel.search(text)

        if (mainViewModel.completions.value!!.isNotEmpty()) {
            navController.navigate(
                route = Destination.WordScreen,
                args = bundleOf(
                    "pArg" to WordParams(
                        word = text,
                        transcription = mainViewModel.getTranscription(text),
                        articles = mainViewModel.getArticles(text),
                    )
                ),
                navOptions = navOptions {
                    launchSingleTop = true
                }

            )

        }
    }

}