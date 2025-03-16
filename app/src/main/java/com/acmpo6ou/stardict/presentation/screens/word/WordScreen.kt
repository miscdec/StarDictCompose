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

package com.acmpo6ou.stardict.presentation.screens.word

import android.content.Context
import android.speech.tts.TextToSpeech
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Unspecified
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.acmpo6ou.stardict.LocalTTS
import org.yage.dict.star.WordParams
import com.acmpo6ou.stardict.presentation.ui.component.common.AppBar
import com.acmpo6ou.stardict.presentation.ui.theme.StarDictTheme
import com.acmpo6ou.stardict.utils.checkVolume
import com.acmpo6ou.stardict.utils.formatArticle

@Composable
fun WordScreen(
    p: WordParams,
    navController: NavController
) {
    val fontSize = 30f
//    val fontPath = settingsModel.fontPath.value
    val fontFamily: FontFamily = FontFamily.Default
//
//    fontPath?.let {
//        fontFamily = FontFamily(Font(File(it)))
//    }


    Scaffold(topBar = { AppBar(title = "", navController = navController) }) {
        Column(
            modifier = Modifier
                .padding(it)
                .verticalScroll(rememberScrollState())
        ) {
            var isFavorite by remember { mutableStateOf(false) }
            WordRow(
                word = p.word,
                transcription = p.transcription,
                isFavorite = isFavorite,
                onClickFavorite = {
                    isFavorite = !isFavorite
                },
                fontFamily = fontFamily,
            )
            
            val iter = p.articles.iterator()
            for (article in iter) {
                Article(
                    vocab = article.key,
                    article = article.value,
                    fontSize = fontSize,
                    fontFamily = fontFamily
                )
                if (iter.hasNext()) HorizontalDivider()
            }
        }
    }
}

@Composable
@Preview
fun WordScreenPreview() {
    val params = WordParams(
        "apple", "[trans]",
        mapOf("Universal" to "Article...")
    )
    StarDictTheme {
        WordScreen(
            p = params,
            navController = rememberNavController()
        )
    }
}

@Composable
fun WordRow(
    word: String,
    transcription: String,
    isFavorite:Boolean,
    onClickFavorite: () -> Unit,
    fontFamily: FontFamily,
) {
//    val favorites: List<String> by favoritesModel.favorites.observeAsState(listOf())
    val context: Context = LocalContext.current
    Row(
        modifier = Modifier.padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
        ) {

        Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(word, fontWeight = FontWeight.Bold, fontFamily = fontFamily)
                val tts = LocalTTS.current
                IconButton(
                    onClick = {
                        context.checkVolume()
                        tts?.speak(transcription, TextToSpeech.QUEUE_FLUSH, null, null)
                    },
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.VolumeUp,
                        contentDescription = "play"
                    )
                }
            }

            if (transcription.isNotBlank()) Text(text = transcription)

        }
        Spacer(modifier = Modifier.weight(1f))

            IconButton(
                modifier = Modifier.padding(8.dp),
                onClick = onClickFavorite,
                enabled = isFavorite
            ) {
                Icon(
                    imageVector = Icons.Rounded.Star,
                    modifier = Modifier.size(50.dp),
                    tint = if (isFavorite) Unspecified else LocalContentColor.current,
                    contentDescription = if (isFavorite) {
                        "remove from favorites"
                    } else {
                        "add to favorites"
                    },
                )
            }
    }
}

@Composable
fun Article(
    vocab: String,
    article: String,
    fontSize: Float,
    fontFamily: FontFamily,
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = vocab,
            fontWeight = FontWeight.Bold,
            fontSize = fontSize.sp,
            fontFamily = fontFamily,
        )
        Card(modifier = Modifier.fillMaxWidth()) {
            SelectionContainer {
                Column {
                    Text(
                        text = formatArticle(text = article),
                    )
                }

            }
        }
    }

}
