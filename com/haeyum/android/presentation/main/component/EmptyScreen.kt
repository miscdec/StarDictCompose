package com.haeyum.android.presentation.main.component

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BorderAll
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.haeyum.common.presentation.theme.Black

@Composable
fun EmptyScreen(text: String) {
    Column(
        modifier = Modifier
            .fillMaxSize(1f)
            .padding(bottom = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Default.BorderAll,
            contentDescription = null,
            modifier = Modifier.size(48.dp),
            tint = Black
        )
        Text(text = text, color = Black)
    }
}

@Preview
@Composable
fun EmptyScreenTest(){
    Surface {
        EmptyScreen(text = "Test")
    }
}