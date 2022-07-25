package ru.netology.mynmedia

data class Post(
    val id: Long,
    val author: String,
    val published: String,
    val content: String,
    var like: Int = 0,
    var shared: Int = 0,
    var liked: Boolean
)
