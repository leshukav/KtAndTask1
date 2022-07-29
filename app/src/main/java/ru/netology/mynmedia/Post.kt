package ru.netology.mynmedia


data class Post(
    val id: Long,
    val author: String,
    val published: String,
    val content: String,
    val like: Int = 999,
    val shared: Int = 500,
    val likedMy: Boolean
)
