package ru.netology.mynmedia

data class Post(
    val id: Long,
    val author: String,
    val published: String,
    val content: String,
    var like: Int = 17599,
    var shared: Int = 10500,
    var liked: Boolean
)
