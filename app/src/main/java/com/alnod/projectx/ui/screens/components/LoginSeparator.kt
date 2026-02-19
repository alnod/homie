package com.alnod.projectx.ui.screens.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun OrDivider(
    text : String
) {

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Divider(
            modifier = Modifier.weight(1f),
            thickness = 1.dp,
            color = Color.LightGray
        )

        Text(
            text,
            modifier = Modifier.padding(horizontal = 8.dp),
            color = Color.Gray,
            fontSize = 12.sp
        )

        Divider(
            modifier = Modifier.weight(1f),
            thickness = 1.dp,
            color = Color.LightGray
        )
    }
}
