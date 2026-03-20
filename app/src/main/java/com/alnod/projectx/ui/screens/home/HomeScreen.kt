package com.alnod.projectx.ui.screens.home


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.alnod.projectx.ui.screens.components.FloatingBottomBar
import com.alnod.projectx.ui.screens.components.PostCard
import com.alnod.projectx.ui.screens.components.StoriesSection
import androidx.compose.material3.MaterialTheme
import com.alnod.projectx.data.sampleUsers
import com.alnod.projectx.navigation.ROUT_CHAT
import com.alnod.projectx.ui.screens.components.HomeTopBar
import com.alnod.projectx.viewmodels.PostViewModel

@Composable
fun HomeScreen(navController: NavController,
    onProfileClick: () -> Unit,
    viewModel: PostViewModel = viewModel()
) {
    val posts by viewModel.posts.collectAsState()

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        // MAIN CONTENT
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 100.dp) // space for floating bar
        ) {
            item { HomeTopBar() }
            item { StoriesSection(users = sampleUsers) }
            
            items(posts) { post ->
                PostCard(
                    post = post,
                    onLikeClick = { viewModel.toggleLike(post.id) }
                )
            }
        }

        // FLOATING BAR
        FloatingBottomBar(
            modifier = Modifier.align(Alignment.BottomCenter),
            onItemSelected = { index ->
                when (index) {
                    1 -> navController.navigate(ROUT_CHAT)
                    2 -> onProfileClick()
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
