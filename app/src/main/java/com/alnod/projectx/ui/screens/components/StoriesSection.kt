package com.alnod.projectx.ui.screens.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.ui.graphics.Color
import com.alnod.projectx.models.User

@Composable
fun StoriesSection(
    users: List<User>,
    onUserClick: (User) -> Unit = {}
) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 8.dp)
    ) {
        items(users) { user ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(8.dp)
                    .clip(CircleShape) // Optional: clips the ripple effect to a circle
                    .clickable { onUserClick(user) }
                    .padding(4.dp) // Extra padding for the ripple
            ) {
                SubcomposeAsyncImage(
                    model = user.imageUrl,
                    contentDescription = user.name,
                    modifier = Modifier
                        .size(70.dp)
                        .clip(CircleShape),
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
