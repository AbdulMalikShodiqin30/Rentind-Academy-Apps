package com.malik.rentindacademy.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.malik.rentindacademy.ui.theme.Purple

@Composable
fun DetailRow(
    modifier: Modifier = Modifier,
    price: String,
    duration: String,
    session: String,
) {
    Row(
        modifier = modifier
            .height(IntrinsicSize.Min)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
        DetailRowItem(
            title = "Harga",
            content = price,
        )
        Divider(
            color = Purple,
            modifier = Modifier
                .fillMaxHeight()
                .width(2.dp)
        )
        DetailRowItem(
            title = "Durasi",
            content = duration,
        )
        Divider(color = Purple,
            modifier = Modifier
                .fillMaxHeight()
                .width(2.dp))
        DetailRowItem(
            title = "Sesi",
            content = session,
        )
    }
}