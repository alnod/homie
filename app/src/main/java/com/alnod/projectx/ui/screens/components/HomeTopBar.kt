package com.alnod.projectx.ui.screens.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alnod.projectx.ui.theme.blue


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar() {
    TopAppBar(
        title = {
            Text(
                text = "Homie",
                style = MaterialTheme
                    .typography.titleLarge,
                modifier = Modifier.padding(start = 90.dp, end = 90.dp),
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = blue
            )
        },
        navigationIcon = {
            IconButton(
                onClick = {},
                modifier = Modifier.padding(start = 15.dp),
                colors = IconButtonDefaults.iconButtonColors(
                    contentColor = blue
                )
            ) { Icon(Icons.Filled.Dashboard, contentDescription = null)}
        },
        actions = {
            IconButton(onClick = {},
                modifier = Modifier.padding(end = 15.dp)) {
                Icon(Icons.Default.Notifications, contentDescription = null)
            }
        }
    )
}
