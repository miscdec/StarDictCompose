package com.acmpo6ou.stardict.presentation.screens.main.dictionary

import android.view.inputmethod.InputMethodManager
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import splitties.systemservices.inputMethodManager

@Composable
fun SearchField(
    textFieldValue: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    onSearchClick: (String) -> Unit,
    onClearText: () -> Unit,
) {

    val focusManager = LocalFocusManager.current
    val focusRequester = remember { FocusRequester() }
    val focusView = LocalView.current.findFocus()

    OutlinedTextField(
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .fillMaxWidth()
            .focusRequester(focusRequester),
        value = textFieldValue,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        singleLine = true,
        onValueChange = onValueChange,
        leadingIcon = {
            Icon(Icons.Default.Search, "")
        },
        trailingIcon = {
            if (textFieldValue.text.isNotEmpty())
                IconButton(
                    content = {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = "clear search"
                        )
                    },
                    onClick = {
                        onClearText()
                        focusRequester.requestFocus()
                        inputMethodManager.showSoftInput(
                            focusView,
                            InputMethodManager.SHOW_IMPLICIT,
                        )
                    }
                )
        },
        keyboardActions = KeyboardActions(
            onSearch = {
                focusManager.clearFocus(true)
                onSearchClick(textFieldValue.text)
            }
        ),
        textStyle = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal
        ),
    )

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
}