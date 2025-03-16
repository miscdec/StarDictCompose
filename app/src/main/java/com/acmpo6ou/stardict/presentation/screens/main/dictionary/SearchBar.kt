package com.acmpo6ou.stardict.presentation.screens.main.dictionary

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SearchBox(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (newValue: String) -> Unit,
    isSearching: Boolean,
    onSearchClick: () -> Unit,
    onClearText: () -> Unit,
) {
    Box(modifier = modifier.wrapContentWidth()) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
//            color = myColors.searchBoxBackground
        ) {
            Box(modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterStart
            ) {

                BasicTextField(
                    modifier = Modifier
                        .onEnterPress {
                            onSearchClick()
                        },
                    value = value,
                    onValueChange = onValueChange,
                    enabled = !isSearching,
                    singleLine = true,
//                    cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
                    keyboardActions = KeyboardActions(
                        onSearch = {
                            onSearchClick()
                        }
                    ),
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Words,
                        imeAction = ImeAction.Search
                    ),
                    textStyle = TextStyle(
//                        color = myColors.onSearchBox.copy(LocalContentColor.current.alpha),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal
                    ),
                    decorationBox = {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            it()
                            if (value.isEmpty()) Text(
                                "Search...",
                                fontSize = 16.sp,
//                                color = myColors.onSearchBox.copy(0.6f)
                            ) else IconButton(
                                modifier = Modifier.align(Alignment.CenterEnd).padding(end = 32.dp),
                                onClick = {
                                    onClearText()
                                    onValueChange("")
                                },
                                enabled = !isSearching
                            ) {
                                Icon(
                                    Icons.Filled.Close,
                                    "Clear",
//                                    tint = myColors.onSearchBox.copy(LocalContentColor.current.alpha)
                                )
                            }
                        }
                    }
                )

                IconButton(
                    modifier = Modifier.align(Alignment.CenterEnd),
                    onClick = {
                        onSearchClick()
                    },
                    enabled = !isSearching && value.isNotBlank()
                ) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "Search",
//                        tint = myColors.onSearchBox.copy(LocalContentColor.current.alpha)
                    )
                }
            }
        }
        Box(Modifier.align(Alignment.BottomCenter).offset(y = 4.dp).height(4.dp).fillMaxWidth()) {
            AnimatedVisibility(
                isSearching,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
            }
        }
    }
}


fun Modifier.onEnterPress(onPress: () -> Unit) =
    this.onKeyEvent {
        return@onKeyEvent if (it.key == Key.Enter) {
            onPress()
            true
        } else false
    }

//val myColors: MyColors
//    @Composable
//    get() = if (isSystemInDarkTheme()) MyDarkColors else MyLightColors
//
//class MyColors(
//    val background: Color,
//    val collinsRed: Color,
//    val searchBoxBackground: Color,
//    val onSearchBox: Color,
//)
//
//val MyLightColors = MyColors(
//    background = Color(0xFFFAFAFA),
//    collinsRed = Color(0xFFD32F2F),
//    searchBoxBackground = Color(0xFFEEEEEE),
//    onSearchBox = Color.Black,
//)
//
//val MyDarkColors = MyColors(
//    background = Color(0xFF212121),
//    collinsRed = Color(0xFFE57373),
//    searchBoxBackground = Color(0xFF424242),
//    onSearchBox = Color.White
//)
//
//internal val lightColors = lightColorScheme(
//    primary = MyLightColors.collinsRed,
//    onPrimary = Color.White,
//    background = MyLightColors.background
//)
//
//internal val darkColors = darkColorScheme(
//    primary = MyDarkColors.collinsRed,
//    onPrimary = Color.White,
//    background = MyDarkColors.background
//)