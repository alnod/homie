package com.alnod.projectx.ui.screens.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.SubcomposeAsyncImage
import com.alnod.projectx.data.sampleUsers
import com.alnod.projectx.models.User
import com.alnod.projectx.navigation.ROUT_CHAT_DETAIL
import com.alnod.projectx.ui.theme.blue

@Composable
fun ChatScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(blue)
    ) {
        Column {
            ChatHeader(onUserClick = { user ->
                navController.navigate("$ROUT_CHAT_DETAIL/${user.id}")
            })

            Surface(
                modifier = Modifier.fillMaxSize(),
                color = Color.White,
                shape = RoundedCornerShape(topStart = 35.dp, topEnd = 35.dp)
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(top = 24.dp, bottom = 100.dp)
                ) {
                    item {
                        SearchAndTabs()
                        Spacer(modifier = Modifier.height(24.dp))
                    }

                    item {
                        SectionHeader(title = "Pinned Message(2)")
                    }
                    items(sampleUsers.take(2)) { user ->
                        ChatItem(
                            user = user,
                            lastMessage = "Hey, it's been a while since we...",
                            time = "13:00 pm",
                            unreadCount = 0,
                            onClick = { navController.navigate("$ROUT_CHAT_DETAIL/${user.id}") }
                        )
                    }

                    item {
                        Spacer(modifier = Modifier.height(16.dp))
                    }

                    item {
                        SectionHeader(title = "All Message(8)")
                    }
                    items(sampleUsers) { user ->
                        ChatItem(
                            user = user,
                            lastMessage = "Sounds good! See you then.",
                            time = "08:30 am",
                            unreadCount = if (user.id == 2) 2 else 0,
                            onClick = { navController.navigate("$ROUT_CHAT_DETAIL/${user.id}") }
                        )
                    }
                }
            }
        }

        FloatingActionButton(
            onClick = { },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 110.dp, end = 24.dp),
            containerColor = blue,
            contentColor = Color.White,
            shape = CircleShape
        ) {
            Icon(Icons.Default.Add, contentDescription = "New Chat")
        }
    }
}

@Composable
fun ChatHeader(onUserClick: (User) -> Unit) {
    Column(
        modifier = Modifier.padding(horizontal = 24.dp, vertical = 40.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(text = "Hi, Alnod!", color = Color.White.copy(alpha = 0.7f), fontSize = 14.sp)
                Text(text = "You Received", color = Color.White, fontSize = 18.sp)
                Text(text = "48 Messages", color = Color.White, fontSize = 28.sp, fontWeight = FontWeight.Bold)
            }
            Box(
                modifier = Modifier
                    .size(45.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color.White.copy(alpha = 0.2f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(imageVector = Icons.Default.Dashboard, contentDescription = null, tint = Color.White)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(text = "Contact List", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Medium)
        Spacer(modifier = Modifier.height(16.dp))

        LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            items(sampleUsers) { user ->
                ContactItem(user = user, onClick = { onUserClick(user) })
            }
        }
    }
}

@Composable
fun ContactItem(user: User, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable { onClick() }
    ) {
        SubcomposeAsyncImage(
            model = user.imageUrl,
            contentDescription = null,
            modifier = Modifier
                .size(55.dp)
                .clip(CircleShape)
                .background(Color.White.copy(alpha = 0.1f)),
            contentScale = ContentScale.Crop,
            loading = { Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) { CircularProgressIndicator(modifier = Modifier.size(20.dp), strokeWidth = 2.dp, color = Color.White) } },
            error = { Icon(Icons.Default.AccountCircle, contentDescription = null, tint = Color.White.copy(alpha = 0.3f), modifier = Modifier.fillMaxSize()) }
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = user.name.split(" ")[0], color = Color.White, fontSize = 12.sp)
    }
}

@Composable
fun SearchAndTabs() {
    var selectedTab by remember { mutableIntStateOf(0) }

    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(Icons.Default.Search, contentDescription = null, tint = Color.Gray, modifier = Modifier.size(24.dp))
        Spacer(modifier = Modifier.width(16.dp))

        Row(
            modifier = Modifier.weight(1f).height(40.dp).clip(RoundedCornerShape(20.dp)).background(Color(0xFFF5F5F5)).padding(4.dp)
        ) {
            TabItem(title = "Direct Message", isSelected = selectedTab == 0, modifier = Modifier.weight(1f)) { selectedTab = 0 }
            TabItem(title = "Group", isSelected = selectedTab == 1, modifier = Modifier.weight(1f)) { selectedTab = 1 }
        }
    }
}

@Composable
fun TabItem(title: String, isSelected: Boolean, modifier: Modifier, onClick: () -> Unit) {
    Box(
        modifier = modifier.fillMaxHeight().clip(RoundedCornerShape(20.dp)).background(if (isSelected) blue else Color.Transparent).clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(text = title, color = if (isSelected) Color.White else Color.Gray, fontSize = 13.sp, fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal)
    }
}

@Composable
fun SectionHeader(title: String) {
    Text(text = title, color = Color.Gray, fontSize = 14.sp, fontWeight = FontWeight.Medium, modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp))
}

@Composable
fun ChatItem(user: User, lastMessage: String, time: String, unreadCount: Int, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = 24.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        SubcomposeAsyncImage(
            model = user.imageUrl,
            contentDescription = null,
            modifier = Modifier.size(60.dp).clip(CircleShape).background(Color.LightGray.copy(alpha = 0.2f)),
            contentScale = ContentScale.Crop,
            loading = { Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) { CircularProgressIndicator(modifier = Modifier.size(20.dp), strokeWidth = 2.dp) } },
            error = { Icon(Icons.Default.AccountCircle, contentDescription = null, tint = Color.LightGray, modifier = Modifier.fillMaxSize()) }
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column(modifier = Modifier.weight(1f)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = user.name, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text(text = time, color = Color.Gray, fontSize = 12.sp)
            }
            Spacer(modifier = Modifier.height(4.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = lastMessage, color = Color.Gray, fontSize = 14.sp, maxLines = 1)
                if (unreadCount > 0) {
                    Box(modifier = Modifier.size(20.dp).clip(CircleShape).background(Color.Red), contentAlignment = Alignment.Center) {
                        Text(text = unreadCount.toString(), color = Color.White, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ChatScreenPreview() {
    ChatScreen(navController = rememberNavController())
}
