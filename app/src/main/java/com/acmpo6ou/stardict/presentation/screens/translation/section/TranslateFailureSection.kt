package com.acmpo6ou.stardict.presentation.screens.translation.section

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TranslateFailureSection(title: String, description: String) {
    Text(
        text = title,
        modifier = Modifier.fillMaxWidth().padding(top = 4.dp),
        fontSize = 20.sp
    )
    Text(
        text = description,
        fontSize = 16.sp
    )
}