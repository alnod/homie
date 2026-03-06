package com.alnod.projectx.ui.screens.home


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.alnod.projectx.ui.screens.components.FloatingBottomBar
import com.alnod.projectx.ui.screens.components.PostCard
import com.alnod.projectx.ui.screens.components.StoriesSection
import androidx.compose.material3.MaterialTheme
import com.alnod.projectx.ui.screens.components.HomeTopBar

@Composable
fun HomeScreen(navController: NavController,
    onProfileClick: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        // MAIN CONTENT
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 100.dp) // space for floating bar
        ) {
            item { HomeTopBar() }
            item { StoriesSection() }
            items(5) { PostCard() }
        }

        // FLOATING BAR
        FloatingBottomBar(
            modifier = Modifier.align(Alignment.BottomCenter),
            onItemSelected = { index ->
                if (index == 2) { // Index 2 is the Person icon
                    onProfileClick()
                }
            },
            selectedIndex = 0
        )
    }
}

@Preview(
    showBackground = true,
    )
@Composable
fun HomeScreenLightPreview() {

    MaterialTheme {
        HomeScreen(
            navController = rememberNavController(),
            onProfileClick = {}
        )
    }
}
