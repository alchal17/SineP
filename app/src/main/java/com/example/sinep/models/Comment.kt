package com.example.sinep.models

data class Comment(
    val content: String,
    val post: Post,
    val user: User,
    val like: Int = 0,
    val dislike: Int = 0
)
