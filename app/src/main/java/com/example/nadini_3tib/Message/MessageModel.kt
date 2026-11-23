package com.example.nadini_3tib.Message

data class MessageModel(
    val senderName: String,
    val messageText: String,
    val avatarUrl: String,
    val distance: String,
    val travelTime: String,
    val kategori: String,
    val id: Int = 0
)