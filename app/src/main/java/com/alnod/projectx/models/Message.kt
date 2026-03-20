package com.alnod.projectx.models

data class Message(
    val id: Int,
    val text: String,
    val isFromMe: Boolean,
    val time: String
)
