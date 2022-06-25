package com.example.tasksocial.model

data class Feed(
    val imageUrl: String?,
    val description: String,
    val like: Boolean,
    val likecount: Int,
    val comments: List<Comment>?,
    val type: Int,
)