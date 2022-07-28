package ru.netology.mynmedia


data class Post(
    val id: Long,
    val author: String,
    val published: String,
    val content: String,
    var like: Int = 17599,
    var shared: Int = 500,
    val likedMy: Boolean
)
