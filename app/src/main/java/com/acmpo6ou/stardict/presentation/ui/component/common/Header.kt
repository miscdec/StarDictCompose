package com.acmpo6ou.stardict.presentation.ui.component.common

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Title
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Header(title: String, imageVector: ImageVector? = null, onClick: () -> Unit = {}) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(
                text = title,
                modifier = Modifier.align(Alignment.Center),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            if (imageVector != null) {
                IconButton(onClick = onClick, modifier = Modifier.align(Alignment.CenterEnd)) {
                    Icon(
                        imageVector = imageVector,
                        contentDescription = "Close",
                    )
                }
            }
            else {
                Spacer(modifier = Modifier.size(48.dp))
            }
        }

        HorizontalDivider()
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Preview
@Composable
fun HeaderPreview(){
    Surface {
        MaterialTheme {
            Header(title = "Title", imageVector = Icons.Default.Title)
        }
    }

}