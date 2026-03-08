package com.alnod.projectx.ui.screens.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.ChatBubbleOutline
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alnod.projectx.R
import com.alnod.projectx.ui.theme.Orange

@Composable
fun PostCard() {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(30.dp)
    ) {
        Box() {

            Image(
            painter = painterResource(R.drawable.post1),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            contentScale = ContentScale.Crop
        )


            Column {

            // Header
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(12.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.profile1),
                    contentDescription = null,
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop

                )

                Spacer(modifier = Modifier.width(8.dp))

                Column {
                    Text("Alana maesya",
                        color = Color.White,
                        fontWeight = FontWeight.Bold)
                    Text("@naisyaatxt",
                        color = Color.White,
                        fontSize = 12.sp)
                }

                Spacer(modifier = Modifier.weight(1f))

                Icon(Icons.Default.MoreVert,
                    tint = Color.Black, contentDescription = null)
            }

                Spacer(modifier = Modifier.height(170.dp))
            // Actions
            Row(
                modifier = Modifier
                    .padding(12.dp)

            ) {
                Icon(Icons.Default.FavoriteBorder,
                    tint = Color.White,
                    contentDescription = null)

                Spacer(modifier = Modifier.width(16.dp))

                Icon(Icons.Default.ChatBubbleOutline,
                    tint = Color.White,
                    contentDescription = null)

                Spacer(modifier = Modifier.width(16.dp))

                Icon(Icons.Default.Send,
                    tint = Color.White,
                    contentDescription = null)
            }
        }
        }
    }
}
