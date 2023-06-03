package com.malik.rentindacademy.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.malik.rentindacademy.ui.theme.Purple

@Composable
fun DetailSection(
    modifier: Modifier = Modifier,
    title: String,
    content: String,
) {
    Column(modifier = modifier) {
        Text(
            text = title,
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp,
            color = Purple
        )
        Text(
            text = content,
            textAlign = TextAlign.Justify,
        )
    }
}