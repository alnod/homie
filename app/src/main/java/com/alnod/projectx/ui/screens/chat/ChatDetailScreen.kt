package com.alnod.projectx.ui.screens.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.SentimentSatisfiedAlt
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
import com.alnod.projectx.models.Message
import com.alnod.projectx.models.User
import com.alnod.projectx.ui.theme.blue

@Composable
fun ChatDetailScreen(navController: NavController, userId: Int) {
    val user = sampleUsers.find { it.id == userId } ?: sampleUsers[0]
    
    val messages = remember {
        mutableStateListOf(
            Message(1, "Hi, I'm doing good, thanks for asking. how about you?", true, "10:00 am"),
            Message(2, "Same here, everything's good. Have you made any plans for vacation yet?", false, "10:01 am"),
            Message(3, "Not really. Do you have any ideas?", true, "10:02 am"),
            Message(4, "What if we take a vacation to Bromo?", false, "10:05 am")
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(blue)
    ) {
        // Custom Top Bar to match the image better
        ChatDetailHeader(user = user, onBack = { navController.popBackStack() })

        // Message List Container
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color.White,
            shape = RoundedCornerShape(topStart = 35.dp, topEnd = 35.dp)
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    contentPadding = PaddingValues(20.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    item {
                        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                            Text(text = "10:00 am", color = Color.Gray, fontSize = 12.sp)
                        }
                    }
                    items(messages) { message ->
                        MessageBubble(message = message, user = user)
                    }
                }
                
                // Input area at the bottom of the white surface
                MessageInputArea()
            }
        }
    }
}

@Composable
fun ChatDetailHeader(user: User, onBack: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 48.dp, bottom = 24.dp, start = 16.dp, end = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onBack) {
            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
        }
        
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = user.name,
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .clip(CircleShape)
                        .background(Color.Green)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(text = "Online", color = Color.White.copy(alpha = 0.8f), fontSize = 12.sp)
            }
        }

        IconButton(onClick = { }) {
            Icon(Icons.Default.MoreVert, contentDescription = "More", tint = Color.White)
        }
    }
}

@Composable
fun MessageBubble(message: Message, user: User) {
    val isMe = message.isFromMe
    
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = if (isMe) Alignment.End else Alignment.Start
    ) {
        Row(
            verticalAlignment = Alignment.Top,
            horizontalArrangement = if (isMe) Arrangement.End else Arrangement.Start,
            modifier = Modifier.fillMaxWidth(0.85f).align(if (isMe) Alignment.End else Alignment.Start)
        ) {
            if (!isMe) {
                SubcomposeAsyncImage(
                    model = user.imageUrl,
                    contentDescription = null,
                    modifier = Modifier
                        .size(35.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
            
            Column(horizontalAlignment = if (isMe) Alignment.End else Alignment.Start) {
                Surface(
                    color = if (isMe) blue else Color(0xFFF2F2F7),
                    shape = RoundedCornerShape(
                        topStart = 20.dp,
                        topEnd = 20.dp,
                        bottomStart = if (isMe) 20.dp else 4.dp,
                        bottomEnd = if (isMe) 4.dp else 20.dp
                    )
                ) {
                    Text(
                        text = message.text,
                        color = if (isMe) Color.White else Color.Black,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
                        fontSize = 15.sp,
                        lineHeight = 20.sp
                    )
                }
                Text(
                    text = message.time,
                    fontSize = 11.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(top = 4.dp, start = if(isMe) 0.dp else 4.dp, end = if(isMe) 4.dp else 0.dp)
                )
            }
        }
    }
}

@Composable
fun MessageInputArea() {
    var text by remember { mutableStateOf("") }
    
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 20.dp)
            .navigationBarsPadding(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { }) {
            Icon(Icons.Default.Add, contentDescription = "Add", tint = Color.Black, modifier = Modifier.size(24.dp))
        }
        
        TextField(
            value = text,
            onValueChange = { text = it },
            modifier = Modifier
                .weight(1f)
                .heightIn(min = 48.dp),
            placeholder = { Text("Type Message", color = Color.Gray) },
            shape = RoundedCornerShape(24.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(0xFFF2F2F7),
                unfocusedContainerColor = Color(0xFFF2F2F7),
                disabledContainerColor = Color(0xFFF2F2F7),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
            trailingIcon = {
                IconButton(onClick = { }) {
                    Icon(Icons.Default.SentimentSatisfiedAlt, contentDescription = "Emoji", tint = Color.Gray)
                }
            }
        )
        
        Spacer(modifier = Modifier.width(12.dp))
        
        IconButton(
            onClick = { },
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(blue)
        ) {
            Icon(Icons.Default.Mic, contentDescription = "Voice", tint = Color.White)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ChatDetailScreenPreview() {
    ChatDetailScreen(navController = rememberNavController(), userId = 1)
}
