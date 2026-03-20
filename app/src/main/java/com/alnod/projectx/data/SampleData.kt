package com.alnod.projectx.data

import com.alnod.projectx.models.Post
import com.alnod.projectx.models.User

val sampleUsers = listOf(
    User(1, "Alnod", "https://ui-avatars.com/api/?name=Alnod&background=random&size=150"),
    User(2, "Brian", "https://ui-avatars.com/api/?name=Brian&background=random&size=150"),
    User(3, "Cynthia", "https://ui-avatars.com/api/?name=Cynthia&background=random&size=150"),
    User(4, "David", "https://ui-avatars.com/api/?name=David&background=random&size=150"),
    User(5, "Ella", "https://ui-avatars.com/api/?name=Ella&background=random&size=150"),
)

val samplePosts = listOf(
    Post(1, sampleUsers[0], "https://picsum.photos/seed/post1/800/800", "Beautiful mountain view!", false),
    Post(2, sampleUsers[1], "https://picsum.photos/seed/post2/800/800", "Working on projectx!", false),
    Post(3, sampleUsers[2], "https://picsum.photos/seed/post3/800/800", "Coffee time!", false),
    Post(4, sampleUsers[3], "https://picsum.photos/seed/post4/800/800", "City lights.", false),
    Post(5, sampleUsers[4], "https://picsum.photos/seed/post5/800/800", "Sunset at the beach.", false),
)
