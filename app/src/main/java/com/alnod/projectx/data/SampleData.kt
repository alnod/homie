package com.alnod.projectx.data

import com.alnod.projectx.models.Post
import com.alnod.projectx.models.User

val sampleUsers = listOf(
    User(1, "Alnod", "https://i.pravatar.cc/150?img=1"),
    User(2, "Brian", "https://i.pravatar.cc/150?img=2"),
    User(3, "Cynthia", "https://i.pravatar.cc/150?img=3"),
    User(4, "David", "https://i.pravatar.cc/150?img=4"),
    User(5, "Ella", "https://i.pravatar.cc/150?img=5"),
)

val samplePosts = listOf(
    Post(1, sampleUsers[0], "https://picsum.photos/id/10/800/800", "Beautiful mountain view!", false),
    Post(2, sampleUsers[1], "https://picsum.photos/id/20/800/800", "Working on projectx!", false),
    Post(3, sampleUsers[2], "https://picsum.photos/id/30/800/800", "Coffee time!", false),
    Post(4, sampleUsers[3], "https://picsum.photos/id/40/800/800", "City lights.", false),
    Post(5, sampleUsers[4], "https://picsum.photos/id/50/800/800", "Sunset at the beach.", false),
)
