package ru.netology.mynmedia

data class Post(
    val id: Long,
    val author: String,
    val published: String,
    val content: String,
    var like: Int = 7599,
    var shared: Int = 1995,
    var liked: Boolean
)
