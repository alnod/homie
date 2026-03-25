package com.alnod.projectx.ui.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.alnod.projectx.models.User
import com.alnod.projectx.ui.theme.blue

@Composable
fun StoriesSection(
    users: List<User>,
    currentUser: User? = null,
    onUserClick: (User) -> Unit = {},
    onAddStoryClick: () -> Unit = {}
) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 8.dp)
    ) {
        // Add Story Item
        item {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(8.dp)
                    .clickable { onAddStoryClick() }
                    .padding(4.dp)
            ) {
                Box(
                    modifier = Modifier.size(70.dp),
                    contentAlignment = Alignment.BottomEnd
                ) {
                    if (currentUser != null) {
                        SubcomposeAsyncImage(
                            model = currentUser.imageUrl,
                            contentDescription = "My Story",
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(CircleShape)
                                .border(2.dp, blue, CircleShape),
                            contentScale = ContentScale.Crop
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Default.AccountCircle,
                            contentDescription = "My Story",
                            modifier = Modifier.fillMaxSize(),
                            tint = Color.Gray
                        )
                    }
                    
                    // Plus Icon Overlay
                    Box(
                        modifier = Modifier
                            .size(20.dp)
                            .clip(CircleShape)
                            .background(blue)
                            .border(2.dp, Color.White, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(12.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "My Story",
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }

        items(users) { user ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(8.dp)
                    .clip(CircleShape)
                    .clickable { onUserClick(user) }
                    .padding(4.dp)
            ) {
                SubcomposeAsyncImage(
                    model = user.imageUrl,
                    contentDescription = user.name,
                    modifier = Modifier
                        .size(70.dp)
                        .clip(CircleShape)
                        .border(2.dp, blue, CircleShape),
                    contentScale = ContentScale.Crop,
                    loading = {
                        CircularProgressIndicator(
                            modifier = Modifier.padding(16.dp),
                            strokeWidth = 2.dp,
                            color = MaterialTheme.colorScheme.primary
                        )
                    },
                    error = {
                        Icon(
                            imageVector = Icons.Default.AccountCircle,
                            contentDescription = "Error loading image",
                            modifier = Modifier.size(70.dp),
                            tint = Color.Gray
                        )
                    }
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = user.name,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}
